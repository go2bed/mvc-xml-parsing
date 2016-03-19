package com.epam.chadov.task3.xml.xml.parsers.impl;

import com.epam.chadov.task3.xml.model.News;
import com.epam.chadov.task3.xml.xml.exceptions.ParseException;
import com.epam.chadov.task3.xml.xml.parsers.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("theDomParser")
public class DOMParser implements Parser<List<News>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DOMParser.class);

    @Autowired
    @Qualifier("defaultDateConverter")
    private Converter<String, Date> dateConverter;

    @Override
    public List<News> apply(InputStream in) {
        List<News> result = new ArrayList<>();
        try {
            DocumentBuilder builder = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder();
            Document document = builder.parse(in);
            result.add(parseDOM(document));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("Can't parse input stream by DOM Parser", e);
            throw new ParseException("Can't parse input stream by DOM Parser", e);
        }
        return result;
    }

    private News parseDOM(Document document) {
        News news = new News();
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("news");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                news.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                String date = eElement.getElementsByTagName("news_date").item(0).getTextContent();
                news.setNewsDate(dateConverter.convert(date));
                news.setBrief(eElement.getElementsByTagName("brief").item(0).getTextContent());
                news.setContent(eElement.getElementsByTagName("content").item(0).getTextContent());
            }
        }

        LOGGER.info(" The DOMParser created object from xml : " + news.toString());
        return news;
    }

}
