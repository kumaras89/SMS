package com.sms.core.repositery;

import com.sms.core.scholarship.ScholarStatus;
import com.sms.core.student.StudentScholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by sathish on 6/20/2016.
 */
public interface StudentScholarRepository extends JpaRepository<StudentScholar, Long>, JpaSpecificationExecutor<StudentScholar> {
    /**
     * @param applicationNumber
     * @return
     */
    StudentScholar findByApplicationNumberIgnoreCase(final String applicationNumber);

    /**
     *
     * @param applicationNumber
     * @param status
     * @return
     */
    @Query("select studentScholar from StudentScholar studentScholar where studentScholar.applicationNumber = :applicationNumber and studentScholar.status = :status")
    StudentScholar findByApplicationNumberWithStatus(@Param("applicationNumber") final String applicationNumber,@Param("status") final ScholarStatus status);

    @Modifying
    @Query( value = "update StudentScholar u set u.status = :status where u.applicationNumber = :applicationNumber")
    int updateStatus(@Param("status") final ScholarStatus status, @Param("applicationNumber") final String applicationNumber);
}
