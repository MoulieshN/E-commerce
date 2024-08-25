package com.mouli.Authentication_Service.Repository;

import com.mouli.Authentication_Service.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Save the user
    User save(User user);

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

    public Optional<User> findByName(String username);
}
