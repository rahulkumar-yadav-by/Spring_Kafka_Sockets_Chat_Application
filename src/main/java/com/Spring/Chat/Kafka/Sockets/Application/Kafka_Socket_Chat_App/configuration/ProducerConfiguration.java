package com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.configuration;


import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.constant.KafkaConstant;
import com.Spring.Chat.Kafka.Sockets.Application.Kafka_Socket_Chat_App.model.Message;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@EnableKafka
@Configuration
public class ProducerConfiguration {

    @Bean //Configuration for producer
    public Map<String, Object> producerConfig(){

        Map<String,Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.BROKER); //kafka server URL
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Data encoding for key
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Data encoding for value
//        System.out.println(config);
        return config;
    }

    @Bean
    public ProducerFactory<String, Message>producerFactory(){
        return new DefaultKafkaProducerFactory<>(
                producerConfig() // method for creating "Producer Instances"  on Kafka Broker
        );
    }

    @Bean
    public KafkaTemplate<String,Message>KafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
