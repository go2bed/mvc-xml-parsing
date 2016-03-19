package com.epam.chadov.task3.xml.database.impl;

import com.epam.chadov.task3.xml.database.GenericDao;
import com.epam.chadov.task3.xml.model.NewsXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
@Configuration
public class NewsXMLDao implements GenericDao<NewsXML> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDao.class);

    @PersistenceContext(name = "newsDS")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<NewsXML> getAll() {
        Query query = em.createQuery("SELECT e FROM News_Xml e", NewsXML.class);
        return (List<NewsXML>) query.getResultList();
    }

    @Override
    public void create(NewsXML newsXML) {
        LOGGER.info("Creating new entity in DB" + newsXML);
        em.merge(newsXML);
    }

    @Override
    public void update(NewsXML newsXML) {
        NewsXML updateNews = em.find(NewsXML.class, newsXML.getId());
        updateNews.setTitle(newsXML.getTitle());
        updateNews.setNewsDate(newsXML.getNewsDate());
        updateNews.setXmlContent(newsXML.getXmlContent());
        updateNews.setSuccess(newsXML.getSuccess());
    }

    @Override
    public void delete(int id) {
        NewsXML newsXML = getById(id);
        em.remove(newsXML);
    }

    @Override
    public NewsXML getById(int id) {
        return em.find(NewsXML.class, id);
    }
}