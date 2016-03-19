package com.epam.chadov.task3.xml.controller;

import com.epam.chadov.task3.xml.database.NewsMySqlDao;
import com.epam.chadov.task3.xml.model.News;
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
    private NewsMySqlDao newsMySqlDao;

    @Autowired
    @Qualifier("domParser")
    private Parser<List<News>> domParser;

    @Autowired
    @Qualifier("saxParser")
    private Parser<List<News>> saxParser;

    @Autowired
    @Qualifier("staxParser")
    private Parser<List<News>> staxParser;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileProcessing(@RequestParam("file") MultipartFile multipartFile,
                                 @RequestParam("parser_type") String parserType,
                                 ModelMap model) throws IOException {
        LOGGER.info(parserType);
        InputStream xmlFile = multipartFile.getInputStream();
        LOGGER.info("Fetching xmlFile");
        if (!xmlValidator.validateXMLSchema(xmlFile)) {
            xmlFile.reset();
            LOGGER.info("Validation of xmlFile is failed");
            model.addAttribute("message", "XSD validate is failed, please, check your XML xmlFile");
            return "xml-validate";
        } else {
            xmlFile.reset();
            LOGGER.info("Validation of xmlFile is successful, start parsing");
            doParseXML(xmlFile, parserType);
            model.addAttribute("message", "Validate is success! Now you can parse your XML xmlFile");
            model.addAttribute("message2", xmlFile);
            return "xml-validate-success";
        }
    }

    private boolean doParseXML(InputStream xmlFile, String parserType) throws IOException {
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
            writeDataOnlyToXmlDatabase();
            LOGGER.info("Parsing was not successful and this data to only XML DB" + newsList.toString());
        } else {
            writeDataToBothSDatabases(newsList);
            LOGGER.info("Parsing was  successful and this data to both DB" + newsList.toString());
        }
        return false;
    }

    private boolean writeDataOnlyToXmlDatabase() {

        return false;
    }

    private boolean writeDataToBothSDatabases(List<News> newsList) {
        for (News news : newsList) {
            newsMySqlDao.create(news);
        }
        return false;
    }
}
//TODO: Exception IO remove and insert own Runtime
