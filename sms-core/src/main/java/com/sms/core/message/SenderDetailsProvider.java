package com.sms.core.message;

import java.util.List;

/**
 * Created by sathish on 7/16/2016.
 */
public interface SenderDetailsProvider {

    /**
     *
     * @return
     */
    List<SenderDetails> createDetails();
}
