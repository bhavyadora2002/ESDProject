package com.bhavya.esdbackend.repo;

import com.bhavya.esdbackend.entity.AlumniEducation;
import com.bhavya.esdbackend.entity.AlumniOrganisation;
import com.bhavya.esdbackend.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepo extends JpaRepository<Organisation, Integer> {
    // You can define custom query methods if needed
}
