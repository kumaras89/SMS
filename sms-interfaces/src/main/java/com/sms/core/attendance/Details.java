package com.sms.core.attendance;

import java.util.Date;

/**
 * Created by Assaycr-04 on 8/23/2016.
 */
public class Details
{
    private long id;
    private Date date;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
