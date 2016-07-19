package com.sms.core.scholarship;

import com.sms.core.student.StudentScholarInfo;

import java.util.List;

/**
 * Created by Ganesan on 02/07/16.
 */
public interface StudentScholarFacade {
    /**
     * @param studentSearchCriteria
     * @return
     */
    List<StudentScholarInfo> search(final StudentScholarSearchCriteria studentSearchCriteria);
}
