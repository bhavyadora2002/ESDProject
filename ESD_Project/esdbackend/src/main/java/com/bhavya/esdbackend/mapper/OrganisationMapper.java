package com.bhavya.esdbackend.mapper;

import com.bhavya.esdbackend.dto.AlumniEducationRequest;
import com.bhavya.esdbackend.dto.AlumniOrganisationRequest;
import com.bhavya.esdbackend.entity.*;
import com.bhavya.esdbackend.dto.LoginRequest;
import com.bhavya.esdbackend.repo.OrganisationRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganisationMapper {
    @Autowired
    private OrganisationRepo organisationRepository;

    public AlumniOrganisation orgEntity(AlumniOrganisationRequest request) {
        Organisation organisation = organisationRepository.findById(request.orgnisation())
                    .orElseThrow(() -> new RuntimeException("Organisation not found"));

        return AlumniOrganisation.builder()
                .alumni(Alumni.builder().alumniId(request.alumni()).build())
                .organisation(organisation)
                .position(request.position())
                .joiningDate(request.joiningDate())
                .leavingDate(request.leavingDate())
                .build();
    }
}
