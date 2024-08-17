package com.asterixcode.order_puller.outbox;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

  private static final Logger log = LoggerFactory.getLogger(Publisher.class);

  @Value("${order.puller.topic.name}")
  private String topicName;

  private final KafkaTemplate<String, String> kafkaTemplate;

  public Publisher(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void publish(String message) {
    // publish the message to Kafka
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

    // add a callback to log the result
    future.whenComplete(
        (result, ex) -> {
          if (ex == null) {
            log.info(
                "Message published: [{}] with offset: {}",
                message,
                result.getRecordMetadata().offset());
          } else {
            log.error("Error publishing message: [{}] due to: {}", message, ex.getMessage());
          }
        });
  }
}
