package com.epam.chadov.task3.xml.controller;

import com.epam.chadov.task3.xml.xml.parsers.DOMParser;
import com.epam.chadov.task3.xml.xml.parsers.SaxParser;
import com.epam.chadov.task3.xml.xml.parsers.StaxParser;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class ParseController {
    private static final Logger logger = LoggerFactory.getLogger(ParseController.class);

    @Autowired
    private DOMParser domParser;

    @Autowired
    private SaxParser saxParser;

    @Autowired
    private StaxParser staxParser;


    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public String parse(@RequestParam("file") String linkOnInputStream,
                        ModelMap model) throws IOException {
        logger.info("Parse " + linkOnInputStream + " this link to object");

        model.addAttribute("message", linkOnInputStream);
        return "xml-parsing-success";
    }
}
