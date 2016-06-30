package com.sms.core.student;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("EmptyClass")
@Entity
@DiscriminatorValue(value = "HSC")
public class HSCMarkDetails extends MarkDetails{
}
