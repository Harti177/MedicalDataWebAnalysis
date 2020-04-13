package com.ovgu.vis.visualizer.AttributeConverter;

import javax.persistence.AttributeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements AttributeConverter<Date, String> {

    @Override
    public String convertToDatabaseColumn(Date attribute) {
        return new SimpleDateFormat("dd/mm/yyyy").format(attribute);
    }

    @Override
    public Date convertToEntityAttribute(String dbData) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/mm/yyyy").parse(dbData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
