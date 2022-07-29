package com.adilsachwani.producerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public static final String IMPORT_CRICKETERS_QUEUE = "import_cricketers_queue";

    // Exchanges
    public static final String CRICKETER_EXCHANGE = "cricketer_exchange";

    // Routing Keys
    public static final String CRICKETER_ROUTING_KEY = "cricketer_key";
    public static final String IMPORT_CRICKETERS_ROUTING_KEY = "import_cricketers_key";

    @Bean
    public Queue cricketerQueue(){
        return new Queue(CRICKETER_QUEUE);
    }

    @Bean
    public Queue importCricketersQueue(){
        return new Queue(IMPORT_CRICKETERS_QUEUE);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(CRICKETER_EXCHANGE);
    }

    @Bean
    public Binding bindingCricketer(Queue cricketerQueue, DirectExchange exchange){
        return BindingBuilder
                .bind(cricketerQueue)
                .to(exchange)
                .with(CRICKETER_ROUTING_KEY);
    }

    @Bean
    public Binding bindingImportCricketers(Queue importCricketersQueue, DirectExchange exchange){
        return BindingBuilder
                .bind(importCricketersQueue)
                .to(exchange)
                .with(IMPORT_CRICKETERS_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
