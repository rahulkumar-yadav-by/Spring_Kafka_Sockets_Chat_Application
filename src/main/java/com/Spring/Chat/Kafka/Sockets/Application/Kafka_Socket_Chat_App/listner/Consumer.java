package com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.listner;


import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.constant.KafkaConstant;
import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = KafkaConstant.TOPIC,groupId = KafkaConstant.GROUP_ID)
    public void consume(Message message){
        System.out.println(message);
        messagingTemplate.convertAndSend("/topic/group",message);

    }
}
