package com.sms.core;

import java.util.List;
import java.util.Optional;

/**
 * Created by sathish on 6/20/2016.
 */
public interface IStudentScholarService<T>
{

    Optional<T> findById(final Long id);

    List<T> findAll();

    Optional<T> save(final T entityType);

}
