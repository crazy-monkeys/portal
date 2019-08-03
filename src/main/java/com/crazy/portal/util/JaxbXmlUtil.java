package com.crazy.portal.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;

/**
 * Created by weiying on 2019/8/2.
 */
public class JaxbXmlUtil {

    public static <T> T convertSoapXmlToJavaBean(String xml, Class<T> t) throws Exception {
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(xml.getBytes()));
        JAXBContext jc = JAXBContext.newInstance(t);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (T) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
    }
}
