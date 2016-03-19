package com.epam.chadov.task3.xml.utils;

import com.epam.chadov.task3.xml.model.NewsXML;
import com.epam.chadov.task3.xml.utils.exceptions.NewsXMLFactoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

@Component("theNewsXMLFactory")
public class NewsXMLFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsXMLFactory.class);

    @Autowired
    @Qualifier("defaultBlobFromStreamConverter")
    private BlobFromStreamConverter converter;

    public NewsXML getNewsXML(MultipartFile multipartFile) {
        NewsXML newsXML = new NewsXML();
        newsXML.setTitle(multipartFile.getOriginalFilename());
        Calendar currentTime = Calendar.getInstance();
        newsXML.setNewsDate(new Date((currentTime.getTime()).getTime()));
        try {
            newsXML.setXmlContent(converter.convert(multipartFile.getInputStream()));
        } catch (IOException e) {
            LOGGER.error("Can't get input stream from file and convert to Blob", e);
            throw new NewsXMLFactoryException("Can't get input stream from file and convert to BLob", e);
        }
        newsXML.setSuccess(false); //It's a default value
        return newsXML;
    }
}
