package com.sms.core;

import com.sms.core.branch.Branch;
import com.sms.core.course.Course;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ram on 6/20/2016.
 */
@Entity
@Table(name = "SMS_TR_ID_CARD")
public class IdCard {

    @Id
    @GeneratedValue
    @Column(name = "IC_ID")
    private Long id;

    @Column(name = "IC_NAME")
    private String name;

    @Column(name = "IC_DOC_ID")
    private Long fmsId;

    @ManyToOne
    @JoinColumn(name = "IC_COURSE")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "IC_BRANCH")
    private Branch branch;

    @Column(name = "IC_VALID_UPTO")
    private Date validUpto;

    @Column(name = "IC_STATUS")
    private String status;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public Branch getBranch() {
        return branch;
    }

    public Date getValidUpto() {
        return validUpto;
    }

    public Long getFmsId() {
        return fmsId;
    }

    public String getStatus() {
        return status;
    }
}
