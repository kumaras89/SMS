package com.sms.core.repositery;

import com.sms.core.batch.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Assaycr-04 on 8/20/2016.
 */
public interface BatchRepository extends JpaRepository<Batch, Long> {
    Batch findByNameIgnoreCase(String batchName);
}
