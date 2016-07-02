package com.sms.core.repositery;

import com.sms.core.student.Student;
import com.sms.core.student.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Student findByCode(String code);

    @Modifying
    @Query("UPDATE Student s SET s.status = :status, s.fmsPhotoId = :fmsPhotoId WHERE s.code = :code")
    int updateStatus(@Param("code") String code, @Param("status") StudentStatus status, @Param("fmsPhotoId") Long fmsPhotoId);
}
