package com.epam.chadov.task3.xml.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Data
@Entity
@Table(name = "NEWS_XML")
public class NewsXML {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Basic
    @Column(name = "FILE_NAME")
    private String title;

    @Basic
    @Column(name = "LOAD_DATE")
    private Date newsDate;

    @Basic
    @Column(name = "XML_CONTENT")
    private Blob xmlContent;

    @Basic
    @Column(name = "SUCCESS")
    private Boolean success;
}
