package com.app.backend.repository;

import com.app.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.repository;
import java.util.optional;

@Repository
public interface UserRepository extends JpaRepository <user, Long>{
    Optional<User> findByUsername (String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}