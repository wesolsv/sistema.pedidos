package com.weszdev.sistema.pedidos.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue estoqueQueue() {
        return new Queue("estoque.queue", true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email.queue", true);
    }

    @Bean
    public Queue logQueue() {
        return new Queue("log.queue", true);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}