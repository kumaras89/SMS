package com.sms.core;

import java.util.List;
import java.util.Optional;

public interface IStudentPortalService<T> {

    Long getCount();

    Optional<T> findById(final Long id);

    List<T> findAll();

    Optional<T> save(final T entityType);

    void delete(final Long id);

    Optional<T> update(final Long id, final T entityType);
}
