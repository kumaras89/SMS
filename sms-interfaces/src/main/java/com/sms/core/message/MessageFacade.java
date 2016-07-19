package com.sms.core.message;

import java.util.List;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
public interface MessageFacade {

    /**
     *
     * @param messageInfo
     */
    List<String> sendMessage(final MessageInfo messageInfo);

    /**
     *
     * @return
     */
    List<MessageInfo> listAll();
}
