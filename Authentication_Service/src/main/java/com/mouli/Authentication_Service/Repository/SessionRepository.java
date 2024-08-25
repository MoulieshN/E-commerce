package com.mouli.Authentication_Service.Repository;

import com.mouli.Authentication_Service.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    public Session save(Session session);
    public Optional<Session> findByTokenAndUserId(String token, Long userId);

}
