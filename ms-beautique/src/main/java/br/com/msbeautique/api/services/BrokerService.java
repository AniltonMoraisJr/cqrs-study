package br.com.msbeautique.api.services;

public interface BrokerService {
  void send(String type, Object data);
}
