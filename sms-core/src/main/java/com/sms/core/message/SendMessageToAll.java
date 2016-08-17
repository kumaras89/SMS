package com.sms.core.message;

import com.sms.core.student.StudentScholarInfo;

import java.util.List;

/**
 * Created by sathish on 8/11/2016.
 */
public interface SendMessageToAll {
    void sendAll(final List<SendingDetails> sendingDetails);
}
