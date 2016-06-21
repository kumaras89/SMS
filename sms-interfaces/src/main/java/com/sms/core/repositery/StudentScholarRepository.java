package com.sms.core.repositery;

import com.sms.core.student.StudentScholar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sathish on 6/20/2016.
 */
public interface StudentScholarRepository extends JpaRepository<StudentScholar ,Long>
{
    StudentScholar findByCodeIgnoreCase(final String code);
}
