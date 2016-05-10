package com.sms.core.repositery;

import com.sms.core.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("StudentRepository")
public interface StudentRepository extends JpaRepository<Student, Long> {

}
