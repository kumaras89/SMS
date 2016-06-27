package com.sms.core.repositery;

import com.sms.core.branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch findByCodeIgnoreCase(final String code);

}
