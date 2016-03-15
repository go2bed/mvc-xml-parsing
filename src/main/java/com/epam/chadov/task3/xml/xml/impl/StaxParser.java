package com.epam.chadov.task3.xml.xml.impl;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service("staxParser")
public class StaxParser implements com.epam.chadov.task3.xml.xml.parsers.StaxParser {
    @Override
    public List parse(InputStream in) {
        return null;
    }
}
