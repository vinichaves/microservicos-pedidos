package br.com.fiap.pedido_receiver_service.infrastructure.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue filaPedido() {
        return new Queue("pedido.queue", false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("pedido.exchange");
    }

    @Bean
    public Binding binding(Queue filaPedido, TopicExchange exchange) {
        return BindingBuilder.bind(filaPedido).to(exchange).with("pedido.novo");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}