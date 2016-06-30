package com.sms.core.common;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Ganesan on 28/06/16. <p></>
 */
public class DateUtils {

    /**
     * @param date
     * @param noOfYears
     * @return
     */
    public static Date addYear(final Date date, final Integer noOfYears) {
        final LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.plusYears(noOfYears).atStartOfDay(ZoneId.systemDefault()).toInstant());

    }

    /**
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Date fromYear(final Year year, final Month month, final int date) {
        return Date.from(LocalDate.of(year.getValue(), month, date).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
