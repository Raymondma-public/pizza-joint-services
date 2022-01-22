package com.pizzajoint.orderpersistenceservice.daos;

import com.pizzajoint.orderpersistenceservice.models.entities.PizzaOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
}
