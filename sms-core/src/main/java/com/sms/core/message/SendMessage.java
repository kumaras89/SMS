package com.sms.core.message;

import java.util.Iterator;
import java.util.List;

/**
 * Created by sathish on 7/13/2016.
 */
public class SendMessage
{

    public static void sendingMessageToAll(List<MessageContain> listOfall,String message)
    {
        Iterator<MessageContain> itr = listOfall.iterator();

        //This is for sending message to everyOne
        while(itr.hasNext())
        {
            MessageContain mc = itr.next();
            message = "Hi,,"+mc.getName()+"\t"+message;
            MessageServiceProvider.sendSms("&numbers="+mc.getPhoneNumber(),"&message="+message);
        }
    }
    public static void sendingMessageToParticular(String phoneNumber,String message)
    {
        MessageServiceProvider.sendSms("&numbers="+phoneNumber,"&message="+message);
    }
}
