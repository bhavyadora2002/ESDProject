package com.bhavya.esdbackend.repo;

import com.bhavya.esdbackend.entity.AlumniCredentials;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumniCredRepo extends JpaRepository<AlumniCredentials,Long> {
    Optional<AlumniCredentials> findByEmail(String email);
}
