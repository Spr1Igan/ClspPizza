package com.example.sprsite.models.repo;

import com.example.sprsite.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
 User findByUsername(String username);

}
