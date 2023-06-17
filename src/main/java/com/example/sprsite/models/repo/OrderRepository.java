package com.example.sprsite.models.repo;

import com.example.sprsite.models.Orders;
import com.example.sprsite.models.Pizza;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Long> {

    @Modifying
    @Query(value="insert into Orders VALUES (Status,id)", nativeQuery=true)
    Iterable<Pizza> MySave(String Status,Long id);
}
