package com.sms.core.repositery;

import com.sms.core.student.StudentScholar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by sathish on 6/20/2016.
 */
public interface StudentScholarRepository extends JpaRepository<StudentScholar ,Long>, JpaSpecificationExecutor<StudentScholar>
{
    StudentScholar findByApplicationNumberIgnoreCase(final String applicationNumber);
}
