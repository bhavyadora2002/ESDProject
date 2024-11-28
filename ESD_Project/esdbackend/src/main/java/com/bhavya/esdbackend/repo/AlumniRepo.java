package com.bhavya.esdbackend.repo;

import com.bhavya.esdbackend.entity.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumniRepo extends JpaRepository<Alumni,Long> {
    Optional<Alumni> findByEmail(String email);

    Alumni getAlumniByEmail(String email);
}
