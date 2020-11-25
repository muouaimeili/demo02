package com.atguigu.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduceTopic {
    public static final String ACTIVEMQ_URL="tcp://192.168.50.135:61616";
    public static final String TOPIC_NAME="topic01";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer messageProducer = session.createProducer(topic);

        for (int i=1;i<=3;i++){
            TextMessage textMessage = session.createTextMessage("****topic的消息"+i);
            messageProducer.send(textMessage);
        }

        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("Topic消息发送到MQ完成");



    }
}
