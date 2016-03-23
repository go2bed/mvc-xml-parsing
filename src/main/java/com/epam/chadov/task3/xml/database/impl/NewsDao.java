package com.epam.chadov.task3.xml.database.impl;

import com.epam.chadov.task3.xml.database.GenericDao;
import com.epam.chadov.task3.xml.model.News;
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
public class NewsDao implements GenericDao<News> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDao.class);

    @PersistenceContext(name = "newsDS")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public List<News> getAll() {
        Query query = em.createQuery("SELECT e FROM News e", News.class);
        return (List<News>) query.getResultList();
    }

    @Override
    public void create(News news) {
        LOGGER.info("creating new entity in DB" + news);
           if (news.getId() == null) {
                em.merge(news);
            } else {
                update(news);
           }
    }

    @Override
    public void update(News news) {
        News updateNews = em.find(News.class, news.getId());
        updateNews.setTitle(news.getTitle());
        updateNews.setNewsDate(news.getNewsDate());
        updateNews.setBrief(news.getBrief());
        updateNews.setContent(news.getContent());

    }

    @Override
    public void delete(int id) {
        News news = getById(id);
        em.remove(news);
    }

    @Override
    public News getById(int id) {
        return em.find(News.class, id);
    }
}