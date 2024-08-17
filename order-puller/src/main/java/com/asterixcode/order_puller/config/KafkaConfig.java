package com.asterixcode.order_puller.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

  @Value("${order.puller.topic.name}")
  private String topicName;

  @Value("${order.puller.topic.partitions}")
  private int numPartitions;

  @Bean
  public NewTopic createOrderPullerTopic() {
    return new NewTopic(topicName, numPartitions, (short) 1);
  }
}
