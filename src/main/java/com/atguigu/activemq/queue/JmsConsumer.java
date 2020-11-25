package com.atguigu.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer {
    public static final String ACTIVEMQ_URL="tcp://192.168.50.135:61616";
    public static final String QUEUE_NAME="queue01";
    public static void main(String[] args) throws JMSException, IOException {


        //1,创建连接工厂,按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2,通过连接工厂，获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
//        3,创建会话session（两个参数，事务/签收）
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        4，创建目的地（具体是队列还是主题topic）
        Queue queue = session.createQueue(QUEUE_NAME);
//        5，创建消息的 消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
       /*
       同步阻塞方式（receive()）
       订阅者或接收者调用MessageConsumer的receive()方法来接收消息，receive方法能够接收到消息之前（或超时之前）将一直阻塞。
       while (true){
            TextMessage textMessage  = (TextMessage) messageConsumer.receive();
            if (null!=textMessage){
                System.out.println("消费者接收到消息"+textMessage.getText());
            }else {
                break;
            }
        }

        */
//       通过监听的方式来消费消息
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (null!=message && message instanceof TextMessage){
                    TextMessage textMessage=(TextMessage)message;
                    try {
                        System.out.println("%%%%%%%消费者接收到消息"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();

//        --------------
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
