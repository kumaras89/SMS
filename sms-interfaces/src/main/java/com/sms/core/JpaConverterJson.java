package com.sms.core;

import javax.persistence.AttributeConverter;

public abstract class JpaConverterJson<To> implements AttributeConverter<To,String> {


    protected abstract Class<To> getJsonClass();

    @Override
    public String convertToDatabaseColumn(final To meta) {
        return null;
    }

    @Override
    public To convertToEntityAttribute(final String dbData) {
        return null;
    }
}
