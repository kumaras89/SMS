package com.sms.core.message;

/**
 * Created by sathish on 7/12/2016.
 */

import com.sms.core.SmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

@Component
public class MessageServiceProvider {

    @Value("${SMS_USERNAME}")
    private String user ;
    @Value("${SMS_PASSWORD}")
    private String password ;
    @Value("${SMS_SENDERID}")
    private String senderId;
    @Value("${SMS_PRIORITY}")
    private String priority;
    @Value("${SMS_SMSTYPE}")
    private String smstype;

    public void sendSms(SmsTemplate smsTemplate)
    {
        try {

            String USER_AGENT = "Mozilla/5.0";
            String SMS_URL = "http://bhashsms.com/api/sendmsg.php?";

            URL obj = new URL(null, SMS_URL, new sun.net.www.protocol.https.Handler());
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "user="+user+"&pass="+password+"&sender="+senderId+"&phone="+smsTemplate.getPhoneNumber()+"&text="+smsTemplate.getMessage()+"&priority="+priority+"&stype="+smstype;
            System.out.println(urlParameters);

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + SMS_URL);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
