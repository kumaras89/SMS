package com.sms.core.message;

/**
 * Created by sathish on 7/13/2016.
 */
public class MessageTemplateCreator {

    public static SmsTemplate createSmsTemplate(final SenderDetails senderDetails, String message) {

        //Using for template
        message = "Hi,," + senderDetails.getName() + "\t" + message;

        return new SmsTemplate("&numbers=" + senderDetails.getPhoneNumber(), "&message=" + message);
    }

}
