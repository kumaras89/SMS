package com.sms.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></>
 */
@Service
@Transactional
public class MessageFacadeImpl implements MessageFacade {

    @Autowired
    private MessageConfig messageConfig;

    /**
     *
     * @param messageInfo
     */
    @Override
    public List<String> sendMessage(final MessageInfo messageInfo){
        return MessageService.sendMessage(messageInfo).with(messageConfig);
    }

    /**
     *
     * @return
     */

    @Override
    public List<MessageInfo> listAll(){
        return MessageService.listAll().with(messageConfig);
    }
}
