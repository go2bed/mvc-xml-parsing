package com.epam.chadov.task3.xml.xml.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XMLValidation {

    private static final Logger logger = LoggerFactory.getLogger(XMLValidation.class);

    public boolean validateXMLSchema(InputStream file, String xsdPath) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            logger.info(schema.toString());
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file));
        } catch (IOException e) {
            logger.error("Check path to XSD File", e);
            return false;
        } catch (SAXException e) {
            logger.error("File is not validated", e);
            return false;
        }
        return true;
    }
}
