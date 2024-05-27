package com.example.notification_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "notification_exchange";

    @Bean
    public FanoutExchange notificationExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email_queue");
    }


    @Bean
    public Binding emailBinding(Queue emailQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(emailQueue).to(exchange);
    }

}


