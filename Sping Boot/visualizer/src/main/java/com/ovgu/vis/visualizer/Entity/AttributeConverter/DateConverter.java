package com.ovgu.vis.visualizer.Entity.AttributeConverter;

import javax.persistence.AttributeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements AttributeConverter<String,Date> {

    @Override
    public String convertToEntityAttribute(Date attribute) {
        return new SimpleDateFormat("dd/mm/yyyy").format(attribute);
    }

    @Override
    public Date convertToDatabaseColumn (String dbData) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/mm/yyyy").parse(dbData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
