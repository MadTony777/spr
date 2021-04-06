package spr;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class JMSconsumer extends JMSpublisher {


    public String JMSconsumer(String environment, String CID, String replyTo, String targetBroker) throws JMSException {
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
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Creating session for seding messages
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Getting the queue 'JCG_QUEUE'
        Destination destination = session.createQueue(replyTo);

        // MessageConsumer is used for receiving (consuming) messages
        MessageConsumer consumer = session.createConsumer(destination);

        // Here we receive the message.
        Message message = consumer.receive();

        // We will be using TestMessage in our example. MessageProducer sent us a TextMessage
        // so we must cast to it to get access to its .getText() method.
        String cid = CID;
        String result="";
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                //System.out.println("Received message '" + textMessage.getText() + "'" + " CID:" + textMessage.getJMSCorrelationID());
                if(cid.equals(textMessage.getJMSCorrelationID())){
                    log.info("Received message '" + textMessage.getText());
                    result = "Message received with CID:" + textMessage.getJMSCorrelationID();
                }
            }
            connection.close();
//        }
        return result;
    }
}

