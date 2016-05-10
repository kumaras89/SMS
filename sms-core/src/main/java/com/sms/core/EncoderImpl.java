package com.sms.core;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.sms.core.Encoder;
import com.sms.core.SmsException;

@Component
public class EncoderImpl implements Encoder {
	
	@Value("${TECHNICAL_PROBLEM}")
	private String technicalProblem;
	
	@Override
	public String encode(final String data) {
		
		try {
			return DigestUtils.md5DigestAsHex(data.getBytes("UTF-8"));
		} catch (final UnsupportedEncodingException e) {
			throw new SmsException(technicalProblem, e);
		}
	}
}
