package br.com.msbeautique.api.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class RabbitMqTopicConfig {
  final static String EXCHANGE_NAME = "beautiqueExchange";

  @Bean
  TopicExchange topicExchange() {
    return new TopicExchange(EXCHANGE_NAME);
  }

  @Bean
  public Queue customerQueue() {
    return new Queue("customerQueue");
  }

  @Bean
  Binding bindingCustomer(Queue customerQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(customerQueue).to(topicExchange).with("customer.#");
  }
}
