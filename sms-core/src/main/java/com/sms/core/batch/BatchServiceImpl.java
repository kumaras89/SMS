package com.sms.core.batch;

import com.sms.core.BaseServiceConvertorImpl;
import com.sms.core.repositery.BatchRepository;
import com.sms.core.repositery.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Assaycr-04 on 8/19/2016.
 */

@Service("batchService")
@Transactional
public class BatchServiceImpl extends BaseServiceConvertorImpl<BatchInfo, Batch>{

    private final CourseRepository courseRepository;

    @Autowired
    public BatchServiceImpl(final BatchRepository batchRepository,
                            final CourseRepository courseRepository) {
        super(batchRepository,
                (batchInfo) ->
                        Batch.toBuilder(batchInfo)
                                .on(Batch::getCourse).set(courseRepository.findByCodeIgnoreCase(batchInfo.getCourseCode()))
                                .build(),
                (batch) -> BatchInfo.toBuilder(batch).build());

        this.courseRepository = courseRepository;
    }

    @Override
    protected Batch buildToPersistObject(final Long id, final BatchInfo batchInfo) {
        return Batch.toBuilder(batchInfo)
                .with(Batch::getCourse, courseRepository.findByCodeIgnoreCase(batchInfo.getCourseCode()))
                .with(Batch::getId,id)
                .build();
    }
}
