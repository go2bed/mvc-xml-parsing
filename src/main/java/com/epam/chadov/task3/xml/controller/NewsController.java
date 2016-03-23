package com.epam.chadov.task3.xml.controller;

import com.epam.chadov.task3.xml.database.impl.NewsDao;
import com.epam.chadov.task3.xml.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class NewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsDao newsDao;

    @Autowired
    @Qualifier("defaultDateConverter")
    private Converter<String, java.sql.Date> dateConverter;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView newsPage(ModelMap modelMap) {
        modelMap.addAttribute("newsList", newsDao.getAll());
        return new ModelAndView("news");
    }

    @RequestMapping(value = "/edit-news", method = RequestMethod.POST)
    public ModelAndView editNewsPage(HttpServletRequest request, ModelMap modelMap) {
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            modelMap.addAttribute("message", "This news is not exists in DataBase");
            return new ModelAndView("edit-news");
        } else {
            Integer id = Integer.valueOf(request.getParameter("id"));
            News news = newsDao.getById(id);
            request.setAttribute("news", news);
            return new ModelAndView("edit-news");
        }
    }


    @RequestMapping(value = "/save-news", method = RequestMethod.POST)
    public ModelAndView saveNews(HttpServletRequest request, ModelMap map) {
        News news = new News();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            news.setId(Integer.valueOf(id));
        }
        news.setTitle(request.getParameter("title"));
        news.setBrief(request.getParameter("brief"));
        news.setContent(request.getParameter("content"));
        String dateFromPage = request.getParameter("newsDate");
        news.setNewsDate(dateConverter.convert(dateFromPage));
        newsDao.create(news);
        map.addAttribute("message", "Your news was successfully saved");
        return new ModelAndView("edit-news");
    }

    @RequestMapping(value = "/delete-list", method = RequestMethod.POST)
    public ModelAndView deleteList(HttpServletRequest request, ModelMap map) {
        LOGGER.info("Data from checkbox " + Arrays.toString(request.getParameterValues("checkbox1")));
        String[] parameterValues = request.getParameter("checkbox1").split(",");
        for (String id : parameterValues) {
            LOGGER.info(id + " This is id for deleting");
            newsDao.delete(Integer.parseInt(id));
        }
        map.addAttribute("message", "Delete was successful");
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/view-news", method = RequestMethod.GET)
    public ModelAndView viewNews(HttpServletRequest request, ModelMap map) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        News news = newsDao.getById(id);
        map.addAttribute("news", news);
        return new ModelAndView("view-news");
    }
}
