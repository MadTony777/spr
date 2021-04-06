package spr;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JMSpublisher extends BaseClass {


    public void JMSpublisher(String environment, String CID, String replyTo, String targetBroker) throws JMSException, IOException {
        Logger log = LoggerFactory.getLogger(UnitTests.class);
        String url = "";
        switch (environment) {
            case "stage":
                switch (targetBroker) {
                    case "ESB_DMZ":
                        url = urldmzstage;
                        break;
                    case "ESB_LAN":
                        url = urllanstage;
                        break;
                }
                break;
            case "test":
                switch (targetBroker) {
                    case "ESB_DMZ":
                        url = urldmztest;
                        break;
                    case "ESB_LAN":
                        url = urllantest;
                        break;

                }
                break;
        }
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("karaf", "karaf", url);
        Destination destination = new ActiveMQQueue(queue);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);
        TextMessage textMessage = session.createTextMessage();

        textMessage.setJMSCorrelationID(CID);
        textMessage.setStringProperty("ReplyTo", replyTo);
        textMessage.setStringProperty("X_VSK_TARGET_BROKER", targetBroker);


        String rightString = new String(Files.readAllBytes(Paths.get(paths + "rtdm.xml")), StandardCharsets.UTF_8);
//        String rightString = rightString1.replace("${ID}", policyId).replace("${NUMBER}", policyId);
        log.info("message send with CID: " + CID);
        textMessage.setText(rightString);
        producer.send(textMessage);
        connection.close();
        session.close();
        producer.close();
    }
}

