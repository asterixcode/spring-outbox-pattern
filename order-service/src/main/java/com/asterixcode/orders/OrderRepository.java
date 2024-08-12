package com.asterixcode.orders;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends ListCrudRepository<Order, Integer> {}
