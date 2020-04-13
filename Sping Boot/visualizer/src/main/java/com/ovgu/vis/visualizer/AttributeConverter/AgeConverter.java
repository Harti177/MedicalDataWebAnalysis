package com.ovgu.vis.visualizer.AttributeConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AgeConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer attribute) {
        String returnString = attribute.equals(-1) ? "N/A" : Integer.toString(attribute);
        return returnString;
    }

    @Override
    public Integer convertToEntityAttribute(String dbData) {
        int returnValue = dbData.equals("N/A") ? -1 : Integer.parseInt(dbData);
        return returnValue;
    }

}
