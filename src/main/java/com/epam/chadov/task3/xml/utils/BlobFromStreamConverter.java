package com.epam.chadov.task3.xml.utils;

import com.epam.chadov.task3.xml.utils.exceptions.BlobFromStreamConverterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Component("defaultBlobFromStreamConverter")
public class BlobFromStreamConverter implements Converter<InputStream, Blob>{
    static final Logger LOGGER = LoggerFactory.getLogger(BlobFromStreamConverter.class);

    @Override
    public Blob convert(InputStream inputStream) {
        Blob result;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count;
        try {
            while ((count = inputStream.read(buffer)) != -1)
                output.write(buffer, 0, count);
        } catch (IOException e) {
            LOGGER.error("Can,t get data from input stream", e);
            throw new BlobFromStreamConverterException("Can,t get data from input stream", e);
        }
        try {
            result = new SerialBlob(output.toByteArray());
        } catch (SQLException e) {
            LOGGER.error("Can't create blob from buffer byte array", e);
            throw new BlobFromStreamConverterException("Can't create blob from buffer byte array", e);
        }
        return result;
    }
}
