package com.epam.chadov.task3.xml.controller;

import com.epam.chadov.task3.xml.controller.exceptions.XMLFileControllerException;
import com.epam.chadov.task3.xml.database.impl.NewsDao;
import com.epam.chadov.task3.xml.database.impl.NewsXMLDao;
import com.epam.chadov.task3.xml.model.News;
import com.epam.chadov.task3.xml.model.NewsXML;
import com.epam.chadov.task3.xml.utils.NewsXMLFactory;
import com.epam.chadov.task3.xml.xml.parsers.Parser;
import com.epam.chadov.task3.xml.xml.validator.XMLValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class XmlFileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlFileController.class);

    @Autowired
    private XMLValidator xmlValidator;

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private NewsXMLDao newsXMLDao;

    @Autowired
    @Qualifier("theNewsXMLFactory")
    private NewsXMLFactory newsXMLFactory;

    @Autowired
    @Qualifier("theDomParser")
    private Parser<List<News>> domParser;

    @Autowired
    @Qualifier("theSaxParser")
    private Parser<List<News>> saxParser;

    @Autowired
    @Qualifier("theStaxParser")
    private Parser<List<News>> staxParser;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileProcessing(@RequestParam("file") MultipartFile multipartFile,
                                 @RequestParam("parser_type") String parserType,
                                 ModelMap model) {
        LOGGER.info(parserType);
        NewsXML newsXML = newsXMLFactory.getNewsXML(multipartFile);
        InputStream xmlFile;
        try {
            xmlFile = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new XMLFileControllerException("Can't get input stream from multipart file", e);
        }
        LOGGER.info("Fetching xmlFile");
        if (!xmlValidator.validateXMLSchema(xmlFile)) {
            xmlFile = resetStream(xmlFile);
            LOGGER.info("Validation of xmlFile is failed");
            model.addAttribute("message", "XSD validate is failed, please, check your XML File");
        } else {
            xmlFile = resetStream(xmlFile);
            LOGGER.info("Validation of xmlFile is successful, start parsing");
            model.addAttribute("message", "Validate is success! Your file was parsed and news saved in DataBase");
        }
        doParseXML(xmlFile, parserType, newsXML);
        return "xml-validate-success";
    }

    private boolean doParseXML(InputStream xmlFile, String parserType, NewsXML newsXML) {
        List<News> newsList = new ArrayList<>();
        switch (parserType.toLowerCase()) {
            case "sax":
                newsList = saxParser.apply(xmlFile);
                break;
            case "stax":
                newsList = staxParser.apply(xmlFile);
                break;
            case "dom":
                newsList = domParser.apply(xmlFile);
                break;
        }
        if (newsList.isEmpty()) {
            LOGGER.info("Parsing was not successful");
            writeDataToXmlDatabase(newsXML);
        } else {
            LOGGER.info("Parsing was successful");
            newsXML.setSuccess(true);
            writeDataToNewsDatabase(newsList);
            writeDataToXmlDatabase(newsXML);
        }
        return false;
    }

    private void writeDataToXmlDatabase(NewsXML newsXML) {
        LOGGER.info("This newsXml get to XML DB " + newsXML);
        newsXMLDao.create(newsXML);
    }

    private void writeDataToNewsDatabase(List<News> newsList) {
        LOGGER.info("This data get to NEWS DB" + newsList);
        for (News news : newsList) {
            newsDao.create(news);
        }
    }


    private InputStream resetStream(InputStream other) {
        try {
            if (other != null) {
                other.reset();
            }
        } catch (IOException e) {
            LOGGER.error("Can't reset input stream data to begin", e);
            throw new XMLFileControllerException("Can't reset input stream data to begin", e);
        }
        return other;
    }
}
