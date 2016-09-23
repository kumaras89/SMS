package com.sms.core.marketing;

import com.sms.core.common.Designation;
import com.sms.core.student.Student;

import java.util.List;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 14:46.
 *  
 */
public interface IMarketingEmployeeSuperiorService {
    /**
     * \
     *
     * @param designation
     * @return
     */
    List<MarketingEmployee> getAllMarketingEmployee(Designation designation);

    /**
     * @param student
     */
    void generateMarketingCommissionSplit(final Student student);
}
