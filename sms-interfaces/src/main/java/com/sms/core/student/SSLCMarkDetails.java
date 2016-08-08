package com.sms.core.student;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ram on 6/25/2016.
 */
@Entity
@DiscriminatorValue(value = "SSLC")
public class SSLCMarkDetails extends MarkDetails{

}
