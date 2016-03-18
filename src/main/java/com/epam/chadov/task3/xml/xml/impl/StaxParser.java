package com.epam.chadov.task3.xml.xml.impl;

import com.epam.chadov.task3.xml.model.News;
import com.epam.chadov.task3.xml.xml.parsers.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("staxParser")
public class StaxParser implements Parser<List<News>>  {
    private static final Logger LOGGER = LoggerFactory.getLogger(StaxParser.class);

    @Autowired
    @Qualifier("defaultDateConverter")
    private Converter<String, Date> dateConverter;

    @Override
    public List<News> apply(InputStream in) {
        List<News> result = new ArrayList<>();

        boolean bTitle = false;
        boolean bDate = false;
        boolean bBrief = false;
        boolean bContent = false;

        try {
           XMLStreamReader reader = XMLInputFactory.newInstance().
                                    createXMLStreamReader(in);
            while(reader.hasNext()){

            }






        } catch (XMLStreamException e) {
            e.printStackTrace();
        }



        return result;
    }
}
