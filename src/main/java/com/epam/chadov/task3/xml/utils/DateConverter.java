package com.epam.chadov.task3.xml.utils;

import com.epam.chadov.task3.xml.utils.exceptions.DateConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component("defaultDateConverter")
public class DateConverter implements Converter<String, Date>{
    static final Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public Date convert(String source) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date uDate = format.parse(source);
            return new Date(uDate.getTime());
        } catch (ParseException e) {
            LOGGER.error("Can't parse date from String", e);
            throw new DateConverterException("Can't parse date from String", e);
        }
    }
}
