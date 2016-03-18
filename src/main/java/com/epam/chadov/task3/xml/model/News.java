package com.epam.chadov.task3.xml.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Data
@Entity
@Table(name = "News")
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

//    @Version
//    @Column(name = "VERSION")
//    private Long version;

    @Basic
    @Column(name = "TITLE")
    private String title;

    @Basic
    @Column(name = "NEWS_DATE")
    private Date newsDate;

    @Basic
    @Column(name = "BRIEF")
    private String brief;

    @Basic
    @Column(name = "CONTENT")
    private String content;

}
