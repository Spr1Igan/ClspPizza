package com.example.sprsite.models.repo;

import com.example.sprsite.models.Orders;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {

    @Modifying
    @Query(value="select i from Orders i where i.user.id = ?1")
    Iterable<Orders> getUserOrders(Long id);
}
