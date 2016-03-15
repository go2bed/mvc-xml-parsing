package com.epam.chadov.task3.xml.xml.parsers;

import java.io.InputStream;
import java.util.List;

@FunctionalInterface
public interface Parser<T> {
    List<T> parse(InputStream in);
}
