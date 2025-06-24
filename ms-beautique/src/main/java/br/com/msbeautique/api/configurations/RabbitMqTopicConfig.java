package br.com.msbeautique.api.configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicConfig {
  public final static String EXCHANGE_NAME = "beautiqueExchange";

  @Bean
  TopicExchange topicExchange() {
    return new TopicExchange(EXCHANGE_NAME);
  }

  @Bean
  public Queue customerQueue() {
    return new Queue("customerQueue");
  }

  @Bean
  public Queue beautyProcedureQueue() {
    return new Queue("beautyProcedureQueue");
  }

  @Bean
  public Queue appointmentQueue() {
    return new Queue("appointmentQueue");
  }

  @Bean
  Binding bindingCustomer(Queue customerQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(customerQueue).to(topicExchange).with("customer.#");
  }

  @Bean
  Binding bindingBeautyProcedure(Queue beautyProcedureQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(beautyProcedureQueue).to(topicExchange).with("beautyProcedure.#");
  }

  @Bean
  Binding bindingAppointment(Queue appointmentQueue, TopicExchange topicExchange) {
    return BindingBuilder.bind(appointmentQueue).to(topicExchange).with("appointment.#");
  }
}
