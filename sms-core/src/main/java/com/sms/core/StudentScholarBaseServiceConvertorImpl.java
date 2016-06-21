package com.sms.core;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created by sathish on 6/20/2016.
 */
@Service
public abstract class StudentScholarBaseServiceConvertorImpl <SOURCE,TARGET> implements IStudentScholarService<SOURCE>
{
    private final Converter<SOURCE, TARGET> sourceConverter;
    private final Converter<TARGET, SOURCE> destinationConverter;
    private final IStudentScholarService<TARGET> studentScholarService;

    public StudentScholarBaseServiceConvertorImpl(final JpaRepository<TARGET, Long> jpaRepository,
                                          final Converter<SOURCE, TARGET> sourceConverter,
                                          final Converter<TARGET, SOURCE> destinationConverter) {
        BiFunction<Long, SOURCE, TARGET> buildPersist = this::buildToPersistObject;
        this.studentScholarService = new StudentScholarBaseServiceImpl<TARGET>(jpaRepository) {
            @Override
            protected TARGET buildToPersistObject(Long id, TARGET entityObject) {
                return buildPersist.apply(id, destinationConverter.convert(entityObject));
            }
        };
        this.sourceConverter = sourceConverter;
        this.destinationConverter = destinationConverter;
    }

    @Override
    public Optional<SOURCE> findById(Long id) {
        return studentScholarService.findById(id).map(destinationConverter::convert);
    }

    @Override
    public List<SOURCE> findAll() {
        return studentScholarService.findAll().stream().map(destinationConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<SOURCE> save(SOURCE entityType) {
        return studentScholarService.save(sourceConverter.convert(entityType)).map(destinationConverter::convert);
    }


    protected abstract TARGET buildToPersistObject(final Long id, final SOURCE entityObject);
}
