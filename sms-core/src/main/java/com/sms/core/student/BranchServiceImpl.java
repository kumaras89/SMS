package com.sms.core.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.core.BaseStudentPortalFindServiceImpl;
import com.sms.core.repositery.BranchRepository;


@Service(BranchServiceImpl.QUALIFIER)
public class BranchServiceImpl extends BaseStudentPortalFindServiceImpl<Branch> {

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
