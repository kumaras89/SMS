package com.sms.core.student;

import com.sms.core.BaseServiceImpl;
import com.sms.core.repositery.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(BranchServiceImpl.QUALIFIER)
public class BranchServiceImpl extends BaseServiceImpl<Branch> {

    public static final String QUALIFIER = "branchService";

    @Autowired
    public BranchServiceImpl(final BranchRepository branchRepository) {
        super(branchRepository);
    }

    @Override
    protected Branch buildToPersistObject(final Long id, final Branch student) {

        return Branch.builder()
                .withId(id)
                .withCode(student.getCode())
                .withName(student.getName())
                .withAddress(student.getAddress())
                .build();
    }
}
