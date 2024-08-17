package com.asterixcode.order_puller.outbox;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class OrderPullerService {

  private static final Logger log = LoggerFactory.getLogger(OrderPullerService.class);

  private final OutboxRepository outboxRepository;
  private final Publisher publisher;

  public OrderPullerService(OutboxRepository outboxRepository, Publisher publisher) {
    this.outboxRepository = outboxRepository;
    this.publisher = publisher;
  }

  @Scheduled(fixedRate = 60000) // 1 minute
  public void pollOutboxMessagesAndPublish() {
    // 1. fetch all unprocessed outbox entries
    List<Outbox> unprocessedRecords = outboxRepository.findAllByProcessedFalse();
    log.info("Found {} unprocessed outbox entries", unprocessedRecords.size());

    // 2. publish each message to Kafka
    unprocessedRecords.forEach(
        outbox -> {
          try {
            publisher.publish(outbox.payload());
            outboxRepository.markAsProcessed(outbox.id());
            log.info("Published message with id: {}", outbox.id());
          } catch (Exception e) {
            log.error("Error processing outbox entry with id: {}", outbox.id());
          }
        });
  }
}
