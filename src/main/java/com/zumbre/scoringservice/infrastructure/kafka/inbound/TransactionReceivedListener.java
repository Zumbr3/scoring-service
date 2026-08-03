package com.zumbre.scoringservice.infrastructure.kafka.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zumbre.scoringservice.infrastructure.kafka.outbound.ApprovedData;
import com.zumbre.scoringservice.infrastructure.kafka.outbound.TransactionApprovedPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionReceivedListener {
  private static final Logger logger = LoggerFactory.getLogger(TransactionReceivedListener.class);
  private final ObjectMapper objectMapper;
  private final TransactionApprovedPublisher publisher;

  public TransactionReceivedListener(ObjectMapper objectMapper, TransactionApprovedPublisher publisher) {
    this.objectMapper = objectMapper;
    this.publisher = publisher;
  }

  @KafkaListener(topics = "${scoring.topics.received}")
  void onTransactionReceived(String payload) {
    try {
      TransactionReceivedEvent event =
          objectMapper.readValue(payload, TransactionReceivedEvent.class);
      logger.info("Json recebido: {}", event);

      ApprovedData simuledTransaction =
          new ApprovedData(event.data().transactionId(), new BigDecimal(15));

      this.publisher.sendApprovedTransaction(simuledTransaction, event.data().accountId());

    } catch (JsonProcessingException exception) {
      // TODO Fatia 6, implement DLQ
      logger.error("payload inválido", exception);
    }
  }
}
