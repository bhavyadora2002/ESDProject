package com.bhavya.esdbackend.repo;

import com.bhavya.esdbackend.entity.AlumniEducation;
import com.bhavya.esdbackend.entity.AlumniOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniOrgRepo extends JpaRepository<AlumniOrganisation, Integer> {
    // You can define custom query methods if needed
}
