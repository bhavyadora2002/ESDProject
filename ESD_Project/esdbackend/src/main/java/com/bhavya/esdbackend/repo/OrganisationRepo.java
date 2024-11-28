package com.bhavya.esdbackend.repo;

import com.bhavya.esdbackend.entity.AlumniEducation;
import com.bhavya.esdbackend.entity.AlumniOrganisation;
import com.bhavya.esdbackend.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganisationRepo extends JpaRepository<Organisation, Integer> {
    @Query("SELECT o.name FROM Organisation o")
    List<String> findAllOrganisationNames();
}
