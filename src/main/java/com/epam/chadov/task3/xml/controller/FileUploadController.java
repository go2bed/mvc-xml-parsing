package com.epam.chadov.task3.xml.controller;

import com.epam.chadov.task3.xml.io.FileBucket;
import com.epam.chadov.task3.xml.xml.validator.XMLValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    // TODO
    @Value("D:/PROJECTS/mvc-xml-parsing/src/main/resources/xsd_schemas/news-validate.xsd")
    private String xsdPath;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile,
                             ModelMap model) throws IOException {

        InputStream file = multipartFile.getInputStream();
        logger.info("Fetching file");
        XMLValidation xmlValidation = new XMLValidation();
        if (!xmlValidation.validateXMLSchema(file, xsdPath)) {
            model.addAttribute("message", "XSD validate is failed, please, check your XML file");
            return "xml-validate";
        } else {
            model.addAttribute("message", "Validate is success! Now you can parse your XML file");
            model.addAttribute("message2", file);
            return "xml-validate-success";
        }
    }
}
