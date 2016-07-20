package com.sms.core.message;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by rmurugaian on 7/19/2016.
 * <p></p>
 */
@Component
public class MarketingEmpSenderDetails implements SenderDetailsProvider {
    @Override
    public Function<MessageConfig, List<SMSDetails>> senderDetails(final MessageInfo messageInfo) {
        return messageConfig -> messageConfig.getMarketingEmployeeService().findAll().stream().map(
            marketingEmployeeInfo -> SMSDetails.builder().on(SMSDetails::getName).set(marketingEmployeeInfo.getName())
                    .on(SMSDetails::getPhoneNumber).set(marketingEmployeeInfo.getPhoneNumber())
                    .on(SMSDetails::getMessage).set(messageInfo.getMessage()).build())
            .collect(Collectors.toList());
    }

    @Override
    public String getType() {
        return "Marketing Employees";
    }
}
