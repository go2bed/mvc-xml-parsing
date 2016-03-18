package com.epam.chadov.task3.xml.controller;

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
import java.util.List;

@Controller
public class XmlFileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlFileController.class);

    @Autowired
    private XMLValidator xmlValidator;

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
    public String fileUploadValidateAndParse(@RequestParam("file") MultipartFile multipartFile,
                                             @RequestParam("parser_type") String parserType,
                                             ModelMap model) throws IOException {
        LOGGER.info(parserType);
        InputStream file = multipartFile.getInputStream();
        LOGGER.info("Fetching file");
        if (!xmlValidator.validateXMLSchema(file)) {
            model.addAttribute("message", "XSD validate is failed, please, check your XML file");
            return "xml-validate";
        } else {
            model.addAttribute("message", "Validate is success! Now you can parse your XML file");
            model.addAttribute("message2", file);
            return "xml-validate-success";
        }
    }
}
