package com.epam.chadov.task3.xml.xml.parsers.impl;

import com.epam.chadov.task3.xml.utils.DateConverter;
import com.epam.chadov.task3.xml.model.News;
import com.epam.chadov.task3.xml.xml.exceptions.ParseException;
import com.epam.chadov.task3.xml.xml.parsers.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service("theSaxParser")
public class SaxParser implements Parser<List<News>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaxParser.class);

    @Autowired
    @Qualifier("defaultDateConverter")
    private DateConverter dateConverter;

    private List<News> result = new ArrayList<>();

    @Override
    public List<News> apply(InputStream in) {
        try {
            SAXParser saxParser = SAXParserFactory
                    .newInstance()
                    .newSAXParser();
            saxParser.parse(in, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("Can't parse input stream by SAX Parser", e);
            throw new ParseException("Can't parse input stream by SAX Parser", e);
        }
        return result;
    }

    private DefaultHandler handler = new DefaultHandler() {

        private News news;
        boolean bTitle = false, bDate = false, bBrief = false, bContent = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("News")) {
                LOGGER.info("Start element is news, creating new object - news");
                news = new News();
                if (result == null) result = new ArrayList<>();
            } else if (qName.equalsIgnoreCase("title")) {
                bTitle = true;
            } else if (qName.equalsIgnoreCase("news_date")) {
                bDate = true;
            } else if (qName.equalsIgnoreCase("brief")) {
                bBrief = true;
            } else if (qName.equalsIgnoreCase("content")) {
                bContent = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("News")) {
                result.add(news);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (bTitle) {
                news.setTitle(new String(ch, start, length));
                bTitle = false;
            } else if (bDate) {
                String date = (new String(ch, start, length));
                news.setNewsDate(dateConverter.convert(date));
                bDate = false;
            } else if (bBrief) {
                news.setBrief(new String(ch, start, length));
                bBrief = false;
            } else if (bContent) {
                news.setContent(new String(ch, start, length));
                bContent = false;
            }
        }
    };
}


