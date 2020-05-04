package com.ovgu.vis.visualizer.Entity.AttributeConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AgeConverter implements AttributeConverter<String,Integer> {

    @Override
    public String convertToEntityAttribute (Integer attribute) {
        String returnString = attribute.equals(-1) ? "N/A" : Integer.toString(attribute);
        return returnString;
    }

    @Override
    public Integer convertToDatabaseColumn(String dbData) {
        int returnValue = dbData.equals("N/A") ? -1 : Integer.parseInt(dbData);
        return returnValue;
    }

}
