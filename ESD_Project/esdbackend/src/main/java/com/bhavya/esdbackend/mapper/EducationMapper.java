package com.bhavya.esdbackend.mapper;

import com.bhavya.esdbackend.dto.AlumniEducationRequest;
import com.bhavya.esdbackend.entity.Alumni;
import com.bhavya.esdbackend.dto.LoginRequest;
import com.bhavya.esdbackend.entity.AlumniCredentials;
import com.bhavya.esdbackend.entity.AlumniEducation;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class EducationMapper {

    public AlumniEducation eduEntity(@Valid AlumniEducationRequest request) {
        return AlumniEducation.builder()
                .degree(request.degree())
                .passingYear(request.passingYear())
                .joiningYear(request.joiningYear())
                .collegeName(request.collegeName())
                .address(request.address())
                .build();
    }
}
