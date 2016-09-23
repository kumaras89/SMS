package com.sms.core.marketing;

import com.sms.core.common.Designation;

import java.util.List;

/**
 *  * @author Ramachandran Murugaian on 23-09-2016 14:46.
 *  
 */
public interface IMarketingEmployeeSuperiorService {
    List<MarketingEmployee> getAllMarketingEmployee(Designation designation);
}
