package com.sms.core.message;

/**
 * Created by sathish on 7/12/2016.
 */
import com.sms.core.SmsException;
import org.springframework.stereotype.Component;

import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class MessageServiceProvider
{

    /*@Value("${SMS_USERNAME}")
    private String user ;
    @Value("${SMS_HASHCODE}")
    private String hash ;*/


    private static String user = "username="+"acharysatish56@gmail.com";

    private static String hash  = "&hash="+"2b70539cebcfc4f049e997d6720af821c9f4b84d";

    private static String sender= "&sender="+"TXTLCL";

    public static void sendSms(String numbers,String message)
    {
        try
        {
            HttpURLConnection conn = (HttpURLConnection) new URL("http://api.textlocal.in/send/?").openConnection();
            String data = user + hash + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            //JsonArray jsonArray = rd.toString();
            //System.out.println(line);
        }
        catch (Exception e) {
            throw new SmsException("SMS Error", e.getCause());
        }
    }
}
