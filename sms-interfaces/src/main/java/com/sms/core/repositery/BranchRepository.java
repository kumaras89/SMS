package com.sms.core.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.core.student.Branch;

@Repository(BranchRepository.QUALIFIER)
public interface BranchRepository extends JpaRepository<Branch, Long> {

    public static final String QUALIFIER = "BranchRepository";

}
