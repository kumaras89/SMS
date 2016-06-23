package com.sms.core.student;

import javaslang.Tuple2;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by rmurugaian on 6/23/2016. <p></p>
 */
public class StudentUpdater {

    /**
     *
     * @param info
     * @return
     */
    public static Function<Tuple2<StudentEnrollmentConfig,Student>,Student>   updateStudent(final StudentInfo info) {
        return tuple -> Student.builder(tuple._2)
            .on(Student::getStudentFees).set(info.getFeesInfos().stream()
                                                                .map(
                                                                    feesInfo -> StudentFeesConverter.convert(feesInfo)
                                                                                          .apply(tuple._1.getFPRepo()))
                                                                .collect(Collectors.toSet()))
            .on(Student::getBranch).set(tuple._1.getBRepo().findByCodeIgnoreCase(info.getBranchCode()))
            .on(Student::getCourse).set(tuple._1.getCRepo().findByCodeIgnoreCase(info.getCourseCode()))
            .on(Student::getScheme).set(tuple._1.getSRepo().findByCodeIgnoreCase(info.getSchemeCode()))
            .on(Student::getMarketingEmployee).set(tuple._1.getMERepo().findByCodeIgnoreCase(info.getMarketingEmployeeCode()))
            .build();
    }
}
