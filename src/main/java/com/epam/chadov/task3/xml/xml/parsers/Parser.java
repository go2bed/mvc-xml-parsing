package com.epam.chadov.task3.xml.xml.parsers;

import java.io.InputStream;
import java.util.function.Function;

public interface Parser<R> extends Function<InputStream, R> {
    R apply(InputStream in);
}
