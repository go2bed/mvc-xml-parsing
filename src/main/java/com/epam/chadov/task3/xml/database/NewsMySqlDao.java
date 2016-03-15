package com.epam.chadov.task3.xml.database;

import com.epam.chadov.task3.xml.model.News;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@Configuration
public class NewsMySqlDao implements GenericDao<News> {

    @PersistenceContext(name = "newsDS")
    private EntityManager manager;


    @Override
    public List<News> getAll() {

        return null;
    }

    @Override
    public void create(News object) {

    }

    @Override
    public void update(News object) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public News getById(Integer id) {
        return null;
    }
}