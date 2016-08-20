package com.sms.core.repositery;

import com.sms.core.student.StudentScholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by sathish on 6/20/2016.
 */
public interface StudentScholarRepository extends JpaRepository<StudentScholar, Long>, JpaSpecificationExecutor<StudentScholar> {
    /**
     * @param applicationNumber
     * @return
     */
    StudentScholar findByApplicationNumberIgnoreCase(final String applicationNumber);

    @Modifying
    @Query( value = "update StudentScholar u set u.status = ?1 where u.applicationNumber = ?2")
    int updateStatus(final String status, final String applicationNumber);
}
