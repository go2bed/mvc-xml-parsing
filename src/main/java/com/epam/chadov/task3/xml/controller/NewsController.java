package com.epam.chadov.task3.xml.controller;

import com.epam.chadov.task3.xml.database.impl.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewsController {

    @Autowired
    private NewsDao newsDao;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView newsPage(ModelMap model) {
        model.addAttribute("newsList", newsDao.getAll());
        return new ModelAndView("news");
    }
}
