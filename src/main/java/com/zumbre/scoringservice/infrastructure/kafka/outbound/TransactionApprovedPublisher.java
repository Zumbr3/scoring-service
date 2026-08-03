package com.zumbre.scoringservice.infrastructure.kafka.outbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zumbre.scoringservice.infrastructure.kafka.inbound.TransactionReceivedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class TransactionApprovedPublisher {
  private final ObjectMapper objectMapper;
  private final KafkaTemplate<String, String> kafkaTemplate;
  @Value("${scoring.topics.approved}") String approvedTopic;

  public TransactionApprovedPublisher(
      ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
    this.objectMapper = objectMapper;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendApprovedTransaction(ApprovedData transactionApproved, UUID accountId) throws JsonProcessingException {
    TransactionApprovedEvent event =
        new TransactionApprovedEvent(
            UUID.randomUUID(),
            "transaction.approved",
            1,
            Instant.now(),
            "scoring-service",
            transactionApproved);

    String stringEvent = objectMapper.writeValueAsString(event);

    this.kafkaTemplate.send(approvedTopic, accountId.toString(), stringEvent);
  }
}
