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
    protected Branch buildToPersistObject(final Long id, final Branch branch) {
        return Branch.toBuilder(branch).on(u -> u.getId()).set(id).build();
    }
}
