package com.epam.chadov.task3.xml.xml.impl;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service("domParser")
public class DOMParser implements com.epam.chadov.task3.xml.xml.parsers.DOMParser {
    @Override
    public List parse(InputStream in) {
        return null;
    }
}
