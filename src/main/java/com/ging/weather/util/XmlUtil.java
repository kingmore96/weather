package com.ging.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.dsig.XMLObject;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class XmlUtil {

    public static Object xmlStrToObject(String xmlStr,Class clazz) throws JAXBException, IOException {
        Object xmlObject = null;
        Reader reader = new StringReader(xmlStr);

        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        xmlObject = unmarshaller.unmarshal(reader);

        if(null != reader){
            reader.close();
        }
        return xmlObject;
    }
}
