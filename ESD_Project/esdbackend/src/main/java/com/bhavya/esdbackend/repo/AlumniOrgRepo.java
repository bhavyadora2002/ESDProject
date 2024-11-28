package com.bhavya.esdbackend.repo;

import com.bhavya.esdbackend.entity.AlumniEducation;
import com.bhavya.esdbackend.entity.AlumniOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumniOrgRepo extends JpaRepository<AlumniOrganisation, Integer> {
    List<AlumniOrganisation> findByAlumni_AlumniId(Integer alumniId);
}
