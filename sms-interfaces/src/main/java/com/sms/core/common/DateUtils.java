package com.sms.core.common;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Ganesan on 28/06/16.
 */
public class DateUtils {

    public static Date addYear(Date date, Integer noOfYears) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.plusYears(noOfYears).atStartOfDay(ZoneId.systemDefault()).toInstant());

    }
}
