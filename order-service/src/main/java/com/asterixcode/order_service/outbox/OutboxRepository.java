package com.asterixcode.order_service.outbox;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends ListCrudRepository<Outbox, Integer> {}
