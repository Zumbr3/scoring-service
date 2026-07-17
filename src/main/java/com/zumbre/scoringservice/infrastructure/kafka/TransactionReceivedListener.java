package com.zumbre.scoringservice.infrastructure.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionReceivedListener {
  private static final Logger logger = LoggerFactory.getLogger(TransactionReceivedListener.class);

  @KafkaListener(topics = "${scoring.topics.received}")
  void onTransactionReceived(String payload) {
    logger.info(payload);
  }
}
