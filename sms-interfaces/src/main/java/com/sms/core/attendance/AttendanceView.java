package com.sms.core.attendance;


import java.util.List;

/**
 * Created by Assaycr-04 on 8/23/2016.
 */
public class AttendanceView
{
    private String name;
    private String code;
    private List<Details> dateList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Details> getDateList() {
        return dateList;
    }

    public void setDateList(List<Details> dateList) {
        this.dateList = dateList;
    }
}
