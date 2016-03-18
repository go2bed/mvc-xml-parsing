package com.epam.chadov.task3.xml.xml.impl;

import com.epam.chadov.task3.xml.model.News;
import com.epam.chadov.task3.xml.xml.parsers.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("staxParser")
public class StaxParser implements Parser<List<News>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StaxParser.class);

    @Autowired
    @Qualifier("defaultDateConverter")
    private Converter<String, Date> dateConverter;

    @Override
    public List<News> apply(InputStream in) {
        List<News> result = new ArrayList<>();

        boolean bTitle = false, bDate = false, bBrief = false, bContent = false;

        try {
            XMLEventReader reader =
                    XMLInputFactory
                            .newInstance()
                            .createXMLEventReader(in);
            News news = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("news")) {
                            LOGGER.info("Start Element : news");
                            news = new News();
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String rollNo = attributes.next().getValue();
                            LOGGER.info("Roll No : " + rollNo);
                        } else if (qName.equalsIgnoreCase("title")) {
                            bTitle = true;
                        } else if (qName.equalsIgnoreCase("news_date")) {
                            bDate = true;
                        } else if (qName.equalsIgnoreCase("brief")) {
                            bBrief = true;
                        } else if (qName.equalsIgnoreCase("content")) {
                            bContent = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (bTitle) {
                            LOGGER.info("Title: "
                                    + characters.getData());
                            news.setTitle(characters.getData());
                            bTitle = false;
                        }
                        if (bDate) {
                            System.out.println("News date: "
                                    + characters.getData());
                            news.setNewsDate(dateConverter
                                    .convert(characters.getData()));
                            bDate = false;
                        }
                        if (bBrief) {
                            System.out.println("Brief: "
                                    + characters.getData());
                            news.setBrief(characters.getData());
                            bBrief = false;
                        }
                        if (bContent) {
                            LOGGER.info("Content: "
                                    + characters.getData());
                            news.setContent(characters.getData());
                            bContent = false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase("news")) {
                            LOGGER.info("End Element : news");
                            result.add(news);
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("Can't parse input stream by Stax Parser", e);
        }
        return result;
    }
}
