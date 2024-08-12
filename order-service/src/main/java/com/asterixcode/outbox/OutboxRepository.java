package com.asterixcode.outbox;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends ListCrudRepository<Outbox, Integer> {}
