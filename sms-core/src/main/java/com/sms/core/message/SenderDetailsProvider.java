package com.sms.core.message;

import java.util.List;
import java.util.function.Function;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
public interface SenderDetailsProvider {

    /**
     * @param messageInfo
     * @return
     */
    Function<MessageConfig,List<SMSDetails>> senderDetails(final MessageInfo messageInfo);

    /**
     * @return
     */
    String getType();
}
