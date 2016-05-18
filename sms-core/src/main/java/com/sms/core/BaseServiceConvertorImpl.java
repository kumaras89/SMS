package com.sms.core;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public abstract class BaseServiceConvertorImpl<SOURCE, TARGET> implements IStudentPortalService<SOURCE> {

    private final Converter<SOURCE, TARGET> sourceConverter;
    private final Converter<TARGET, SOURCE> destinationConverter;
    private final IStudentPortalService<TARGET> studentPortalService;

    public BaseServiceConvertorImpl(final JpaRepository<TARGET, Long> jpaRepository,
                                    final Converter<SOURCE, TARGET> sourceConverter,
                                    final Converter<TARGET, SOURCE> destinationConverter) {
        BiFunction<Long, SOURCE, TARGET> buildPersist = this::buildToPersistObject;
        this.studentPortalService = new BaseServiceImpl<TARGET>(jpaRepository) {
            @Override
            protected TARGET buildToPersistObject(Long id, TARGET entityObject) {
                return buildPersist.apply(id, destinationConverter.convert(entityObject));
            }
        };
        this.sourceConverter = sourceConverter;
        this.destinationConverter = destinationConverter;
    }

    @Override
    public Long getCount() {
        return studentPortalService.getCount();
    }

    @Override
    public Optional<SOURCE> findById(Long id) {
        return studentPortalService.findById(id).map(destinationConverter::convert);
    }

    @Override
    public List<SOURCE> findAll() {
        return studentPortalService.findAll().stream().map(destinationConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<SOURCE> save(SOURCE entityType) {
        return studentPortalService.save(sourceConverter.convert(entityType)).map(destinationConverter::convert);
    }

    @Override
    public void delete(Long id) {
        studentPortalService.delete(id);
    }

    @Override
    public Optional<SOURCE> update(Long id, SOURCE entityType) {
        return studentPortalService.update(id, buildToPersistObject(id, entityType)).map(destinationConverter::convert);
    }

    protected abstract TARGET buildToPersistObject(final Long id, final SOURCE entityObject);
}
