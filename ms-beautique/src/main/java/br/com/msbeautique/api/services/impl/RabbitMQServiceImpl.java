package br.com.msbeautique.api.services.impl;

import br.com.msbeautique.api.configurations.RabbitMqTopicConfig;
import br.com.msbeautique.api.services.BrokerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class RabbitMQServiceImpl implements BrokerService {
  private final ObjectMapper objectMapper;
  private final RabbitTemplate rabbitTemplate;

  public RabbitMQServiceImpl(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
    this.objectMapper = objectMapper;
    this.rabbitTemplate = rabbitTemplate;
  }


  @Override
  public void send(String type, Object data) {
    String routingKey = type + ".#";
    try {
      String jsonData = objectMapper.writeValueAsString(data);
      rabbitTemplate.convertAndSend(RabbitMqTopicConfig.EXCHANGE_NAME, routingKey, jsonData, message -> {
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return message;
      });
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error converting object to json. Error: " + e.getMessage());
    }
  }
}
