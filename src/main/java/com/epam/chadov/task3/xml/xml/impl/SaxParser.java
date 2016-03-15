package com.epam.chadov.task3.xml.xml.impl;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service("saxParser")
public class SaxParser implements com.epam.chadov.task3.xml.xml.parsers.SaxParser {
    @Override
    public List parse(InputStream in) {
        return null;
    }
}
