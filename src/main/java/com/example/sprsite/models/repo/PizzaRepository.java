package com.example.sprsite.models.repo;
import com.example.sprsite.models.Pizza;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

    @Modifying
    @Query(value="select * from pizza p where p.Name like %:Name%", nativeQuery=true)
    Iterable<Pizza> FingByName(String Name);
}