package com.sms.core.student;

import com.sms.core.repositery.*;
import com.sms.core.scholarship.StudentScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rmurugaian on 6/23/2016. <p></p>
 */
@Component
public class StudentEnrollmentConfig {


    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private SchemeRepository schemeRepository;
    @Autowired
    private MarketingEmployeeRepository marketingEmployeeRepository;
    @Autowired
    private FeesParticularRepository feesParticularRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentScholarService studentScholarService;



    public BranchRepository getBRepo() {
        return branchRepository;
    }

    public BatchRepository getBTRepo() {
        return batchRepository;
    }

    public SchemeRepository getSRepo() {
        return schemeRepository;
    }

    public MarketingEmployeeRepository getMERepo() {
        return marketingEmployeeRepository;
    }

    public FeesParticularRepository getFPRepo() {
        return feesParticularRepository;
    }

    public StudentRepository getStuRepo() {
        return studentRepository;
    }

    public StudentScholarService getStudScholarServ(){return studentScholarService;}
}
