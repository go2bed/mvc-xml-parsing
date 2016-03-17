package com.epam.chadov.task3.xml.database;

import com.epam.chadov.task3.xml.model.News;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
@Configuration
public class NewsMySqlDao implements GenericDao<News> {

    @PersistenceContext(name = "newsDS")
    private EntityManager manager;

    @Override
    public List<News> getAll() {
        Query query = manager.createQuery("SELECT e FROM News e", News.class);
        return (List<News>) query.getResultList();
    }

    @Override
    public void create(News news) {
        manager.persist(news);
    }

    @Override
    public void update(News news) {
        News updateNews = manager.find(News.class, news.getId());
        updateNews.setTitle(news.getTitle());
        updateNews.setNewsDate(news.getNewsDate());
        updateNews.setBrief(news.getBrief());
        updateNews.setContent(news.getContent());
    }

    @Override
    public void delete(int id) {
        News news = getById(id);
        manager.remove(news);
    }

    @Override
    public News getById(int id) {
        return manager.find(News.class, id);
    }
}