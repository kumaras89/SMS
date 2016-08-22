package com.sms.core.batch;

import java.util.List;
import java.util.Optional;

/**
 * Created by Assaycr-04 on 8/19/2016.
 */

public interface BatchService {

    /**
     *
     * @param id
     * @return BatchInfo
     */

    Optional<BatchInfo> findById(long id);

    /**
     *
     * @return List of BatchInfo
     */

    List<BatchInfo> findAll();

    /**
     *
     * @param batchInfo
     * @return
     */

    Optional<BatchInfo> save(final BatchInfo batchInfo);

    /**
     *
     * @param id
     * @param batchInfo
     * @return batchInfo
     */

    Optional<BatchInfo> update(final Long id, final BatchInfo batchInfo);

}