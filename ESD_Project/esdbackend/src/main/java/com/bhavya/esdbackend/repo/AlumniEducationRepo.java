package com.bhavya.esdbackend.repo;

import com.bhavya.esdbackend.entity.AlumniEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniEducationRepo extends JpaRepository<AlumniEducation, Integer> {
    AlumniEducation findByAlumni_AlumniId(Integer alumniId);
}
