package com.asterixcode.order_puller.outbox;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OutboxRepository extends ListCrudRepository<Outbox, Integer> {

  // fetch all unprocessed outbox entries
  List<Outbox> findAllByProcessedFalse();

  @Transactional
  @Modifying
  @Query("UPDATE outbox SET processed = true WHERE id = :id")
  void markAsProcessed(Integer id);
}
