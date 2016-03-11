package com.epam.chadov.task3.xml.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "NEWS_DATE")
    private Date newsDate;

    @Column(name = "BRIEF")
    private String brief;

    @Column(name = "CONTENT")
    private String content;




    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", newsDate=" + newsDate +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
