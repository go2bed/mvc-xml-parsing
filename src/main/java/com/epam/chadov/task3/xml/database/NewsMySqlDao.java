package com.epam.chadov.task3.xml.database;

import com.epam.chadov.task3.xml.model.News;
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
public class NewsMySqlDao implements GenericDao<News> {

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
        em.persist(news);
    }

    @Override
    public void update(News news) {
        //TODO  em.merge() ?? diff between persist and merge ??
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