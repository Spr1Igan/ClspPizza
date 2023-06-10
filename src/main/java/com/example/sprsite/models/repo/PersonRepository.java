package com.example.sprsite.models.repo;
import com.example.sprsite.models.Person;
import com.example.sprsite.models.Pizza;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Modifying
    @Query(value="select * from person p where p.login = :login", nativeQuery=true)
    Iterable<Person> FingByLogin(String login);
}
