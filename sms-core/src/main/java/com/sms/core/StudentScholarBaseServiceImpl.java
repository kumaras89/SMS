package com.sms.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 6/20/2016.
 */
@Service
public abstract class StudentScholarBaseServiceImpl<T> implements IStudentScholarService<T>
{
    private final JpaRepository<T, Long> jpaRepository;

    public StudentScholarBaseServiceImpl(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Optional<T> findById(final Long id) {
        return Optional.<T>of((T) this.jpaRepository.findOne(id));
    }

    public List<T> findAll() {
        return this.jpaRepository.findAll();
    }

    public Optional<T> save(final T entityObject) {
        return Optional.of((T) jpaRepository.saveAndFlush(entityObject));
    }

    protected abstract T buildToPersistObject(final Long id, final T entityObject);

}
