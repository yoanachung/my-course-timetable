package com.example.enrollment.domain.course.converter;

import com.example.enrollment.domain.course.enums.Credit;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CreditConverter implements AttributeConverter<Credit, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Credit attribute) {
        return attribute.getCreditValue();
    }

    @Override
    public Credit convertToEntityAttribute(Integer dbData) {
        return Credit.findByCreditValue(dbData);
    }
}
