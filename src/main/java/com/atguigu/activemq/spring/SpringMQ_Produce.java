package com.atguigu.activemq.spring;


import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMQ_Produce produce=(SpringMQ_Produce)ctx.getBean("springMQ_Produce");

        produce.jmsTemplate.send((session)->{
            TextMessage textMessage = session.createTextMessage("spring+activemq整合******case333");
            return textMessage;
        });
        System.out.println("*****send task over");
    }
}
