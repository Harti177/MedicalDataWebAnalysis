package com.ovgu.vis.visualizer.Entity.AttributeConverter;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConverter implements AttributeConverter<String, Timestamp> {

    @Override
    public String convertToEntityAttribute(Timestamp attribute) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = dateTimeFormatter.format(attribute.toLocalDateTime());
        return dateString;
//        return new SimpleDateFormat("dd-MM-yyyy").format(attribute);
    }

    @Override
    public Timestamp convertToDatabaseColumn (String dbData) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(dbData);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(date.getTime());
    }
}
