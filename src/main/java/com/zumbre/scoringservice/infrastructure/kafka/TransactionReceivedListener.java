package com.zumbre.scoringservice.infrastructure.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionReceivedListener {
  private static final Logger logger = LoggerFactory.getLogger(TransactionReceivedListener.class);
  private final ObjectMapper objectMapper;

  public TransactionReceivedListener(ObjectMapper objectMapper){
    this.objectMapper = objectMapper;
  }

  @KafkaListener(topics = "${scoring.topics.received}")
  void onTransactionReceived(String payload) {
    try{
      TransactionReceivedEvent evento = objectMapper.readValue(payload, TransactionReceivedEvent.class);
      logger.info("Json recebido: {}", evento);
    }
    catch (JsonProcessingException exception){
      //TODO Fatia 6, implement DLQ
      logger.error("payload inválido", exception);
    }

  }
}
