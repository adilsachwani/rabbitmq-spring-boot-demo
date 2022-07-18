package com.adilsachwani.producerservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingQueueConfig {

    public static final String CRICKETER_QUEUE = "cricketer_queue";
    public static final String CRICKETER_EXCHANGE_TOPIC = "cricketer_topic";
    public static final String CRICKETER_ROUTING_KEY = "cricketer_routing_key";

    @Bean
    public Queue queue(){
        return new Queue(CRICKETER_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(CRICKETER_EXCHANGE_TOPIC);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(CRICKETER_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
