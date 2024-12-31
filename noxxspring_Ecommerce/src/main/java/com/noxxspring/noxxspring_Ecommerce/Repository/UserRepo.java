package com.noxxspring.noxxspring_Ecommerce.Repository;

import com.noxxspring.noxxspring_Ecommerce.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
