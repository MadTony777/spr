package spr;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import java.io.IOException;

public class Variability {
    public static String reporter(String environment, String CID, String replyTo, String targetBroker) throws IOException, JMSException {
        Logger log = LoggerFactory.getLogger(UnitTests.class);
        JMSpublisher publisher = new JMSpublisher();
        publisher.JMSpublisher(environment , CID, replyTo, targetBroker);
        JMSconsumer consumer = new JMSconsumer();
        String result = consumer.JMSconsumer(environment , CID, replyTo, targetBroker);
        log.info(result);
        return result;
    }

}
