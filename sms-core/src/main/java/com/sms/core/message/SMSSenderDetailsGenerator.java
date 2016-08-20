package com.sms.core.message;

import java.util.List;

/**
 * Created by sathish on 8/11/2016.
 */
public interface SMSSenderDetailsGenerator {
    /**
     * @param sendingDetails
     */
    void createSMSDetails(final List<SendingDetails> sendingDetails);
}
