package com.sms.core.student;

import com.sms.core.common.Builder;
import com.sms.core.repositery.FeesParticularRepository;
import com.sms.core.feesparticular.FeesInfo;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by rmurugaian on 6/23/2016. <p></p>
 */
public class StudentFeesConverter {

    /**
     *
     * @param feesInfo
     * @return
     */
    public static Function<FeesParticularRepository, StudentFees> convert(final FeesInfo feesInfo) {
        return fpRepo -> StudentFees.builder()
                                              .on(StudentFees::getAmount)
                                              .set(feesInfo.getAmount())
                                              .on(sf -> sf.getFeesParticular())
                                              .set(fpRepo.findByCodeIgnoreCase(feesInfo.getFeesParticularCode()))
            .build();
    }

    /**
     *
     * @param fees
     * @return
     */
    public static List<FeesInfo> convertTo(final Set<StudentFees> fees) {
        return fees.stream().map(FeesInfo::toBuilder).map(Builder::build).collect(Collectors.toList());
    }

}
