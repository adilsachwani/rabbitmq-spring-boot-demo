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

    // Queues
    public static final String CRICKETER_QUEUE = "cricketer_queue";

    // Exchanges
    public static final String CRICKETER_EXCHANGE = "cricketer_exchange";

    // Routing Keys
    public static final String CRICKETER_ROUTING_KEY = "cricketer_key";

    @Bean
    public Queue cricketerQueue(){
        return new Queue(CRICKETER_QUEUE);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(CRICKETER_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue cricketerQueue, DirectExchange exchange){
        return BindingBuilder
                .bind(cricketerQueue)
                .to(exchange)
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
