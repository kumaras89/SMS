package com.sms.core.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.core.BaseServiceImpl;
import com.sms.core.repositery.BranchRepository;


@Service(BranchServiceImpl.QUALIFIER)
public class BranchServiceImpl extends BaseServiceImpl<Branch> {

    public static final String QUALIFIER = "BranchServiceImpl";

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
