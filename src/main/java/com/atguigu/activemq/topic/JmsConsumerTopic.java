package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumerTopic {
    public static final String ACTIVE_MQ="tcp://192.168.50.135:61616";
    public static final String TOPIC_NAME="topic01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic sessionTopic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(sessionTopic);
        consumer.setMessageListener(message -> {
            if (null!=message && message instanceof TextMessage){
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println("&&&&TopicMessage是"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();

        consumer.close();
        session.close();
        connection.close();

    }
}
