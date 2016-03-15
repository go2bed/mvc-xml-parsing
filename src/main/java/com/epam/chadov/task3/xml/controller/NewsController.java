package com.epam.chadov.task3.xml.controller;

import com.epam.chadov.task3.xml.database.NewsMySqlDao;
import com.epam.chadov.task3.xml.xml.parsers.DOMParser;
import com.epam.chadov.task3.xml.xml.parsers.SaxParser;
import com.epam.chadov.task3.xml.xml.parsers.StaxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {

    @Autowired
    private DOMParser domParser;

    @Autowired
    private SaxParser saxParser;

    @Autowired
    private StaxParser staxParser;

    @Autowired
    private NewsMySqlDao newsMySqlDao;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView newsPage(ModelMap model) {
        model.addAttribute("newsList", newsMySqlDao.getAll());
        return new ModelAndView("news");
    }
}
