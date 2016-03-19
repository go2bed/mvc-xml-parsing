package com.epam.chadov.task3.xml.xml.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@Component
public class XMLValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLValidator.class);

    @Value("${xsd.news-schema}")
    private String newsSchema;

    public boolean validateXMLSchema(InputStream xmlFile) {
        try {
            Schema schema = SchemaFactory.
                    newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).
                    newSchema(XMLValidator.class.getResource(newsSchema));
            LOGGER.info(schema.toString());
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (IOException e) {
            LOGGER.error("Check path to XSD File", e);
            return false;
        } catch (SAXException e) {
            LOGGER.error("File is not validated", e);
            return false;
        }
        return true;
    }
}
