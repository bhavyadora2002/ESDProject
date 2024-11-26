package com.bhavya.esdbackend.service;


import com.bhavya.esdbackend.dto.AlumniEducationRequest;
import com.bhavya.esdbackend.dto.AlumniOrganisationRequest;
import com.bhavya.esdbackend.dto.LoginRequest;

import com.bhavya.esdbackend.entity.Alumni;
import com.bhavya.esdbackend.entity.AlumniCredentials;
import com.bhavya.esdbackend.entity.AlumniEducation;
import com.bhavya.esdbackend.entity.AlumniOrganisation;
import com.bhavya.esdbackend.exception.ResourceNotFoundException;
import com.bhavya.esdbackend.helper.EncryptionService;
import com.bhavya.esdbackend.helper.JWTHelper;
import com.bhavya.esdbackend.mapper.AlumniMapper;
import com.bhavya.esdbackend.mapper.EducationMapper;

import com.bhavya.esdbackend.mapper.OrganisationMapper;
import com.bhavya.esdbackend.repo.AlumniCredRepo;
import com.bhavya.esdbackend.repo.AlumniOrgRepo;
import com.bhavya.esdbackend.repo.AlumniRepo;
import com.bhavya.esdbackend.repo.AlumniEducationRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AlumniService {
    @Autowired
    private final AlumniCredRepo alumniCredRepo;
    @Autowired
    private final AlumniRepo alumniRepo;
    @Autowired
    private final AlumniEducationRepo alumniEducationRepository;
    @Autowired
    private final AlumniOrgRepo alumniOrgRepo;
    @Autowired
    private final AlumniMapper mapper;
    @Autowired
    private final EducationMapper edumapper;
    @Autowired
    private final OrganisationMapper orgmapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String login(LoginRequest request) {
        AlumniCredentials r = mapper.loginEntity(request);
        String email = r.getEmail();
        String password = r.getPassword();
        AlumniCredentials cred = alumniCredRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No credentials found for email: " + request.email()));
//        if (cred.getPassword().equals(password)) {
//            return "Login successful";
//        }
//        return "Login failed";
        if(!encryptionService.validates(password, cred.getPassword())) {
            return "Wrong Password or Email";
        }
        return jwtHelper.generateToken(request.email());

    }
//
//    public String login(@Valid LoginRequest request) {
//        Customer customer = mapper.loginEntity(request);
//        String email = customer.getEmail();
//        String password = customer.getPassword();
//        Customer cust = repo.findByEmail(email);
////        if (password.equals(cust.getPassword())) {
////            return "Logged in";
////        }
////        else {
////            return "Wrong password";
////        }
//        if(!encryptionService.validates(password, cust.getPassword())) {
//            return "Wrong Password or Email";
//        }
//        return jwtHelper.generateToken(request.email());
//
//    }
//
    public Alumni updateContactInfo(Long id, String newContactNumber, String newEmail) {
        System.out.println("updating email");
        Alumni alumni = alumniRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumni not found with Id: " + id));

            System.out.println("updating email");
            alumni.setEmail(newEmail);
            System.out.println("updating email");
            alumni.setContactNumber(newContactNumber);
        alumniRepo.save(alumni);
        return alumni;
    }

    public AlumniEducation updateEducationQualification(Integer educationId, AlumniEducationRequest request ) {
        AlumniEducation alumniEducation = alumniEducationRepository.findById(educationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid education ID"));
        AlumniEducation r = edumapper.eduEntity(request);
        if(r.getDegree()!=null)
            alumniEducation.setDegree(r.getDegree());
        if(r.getPassingYear()!=null)
            alumniEducation.setPassingYear(r.getPassingYear());
        if(r.getJoiningYear()!=null)
            alumniEducation.setJoiningYear(r.getJoiningYear());
        if(r.getCollegeName()!=null)
            alumniEducation.setCollegeName(r.getCollegeName());
        if(r.getAddress()!=null)
            alumniEducation.setAddress(r.getAddress());

        return alumniEducationRepository.save(alumniEducation);
    }

    public ResponseEntity<String> saveAlumniOrganisation(@Valid AlumniOrganisationRequest request) {
        AlumniOrganisation r = orgmapper.orgEntity(request);
        alumniOrgRepo.save(r);
        return ResponseEntity.ok("Organisation updated successfully");
    }

    public ResponseEntity<String> updateAlumniOrganisation(Integer id, @Valid AlumniOrganisationRequest request) {
        AlumniOrganisation alumniOrganisation = alumniOrgRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work ID"));
        AlumniOrganisation r = orgmapper.orgEntity(request);
        if(r.getPosition()!=null){
            alumniOrganisation.setPosition(r.getPosition());
        }
        if(r.getJoiningDate()!=null){
            alumniOrganisation.setJoiningDate(r.getJoiningDate());
        }
        if(r.getLeavingDate()!=null){
            alumniOrganisation.setLeavingDate(r.getLeavingDate());
        }
        alumniOrgRepo.save(alumniOrganisation);
        return ResponseEntity.ok("Organisation updated successfully");
    }

    public ResponseEntity<String> deleteOrg(Integer id) {
        if(alumniOrgRepo.findById(id).isPresent()) {
            alumniOrgRepo.deleteById(id);
            return ResponseEntity.ok("Organisation deleted successfully");
        }else{
            return ResponseEntity.ok("Entry not found");        }
    }

}
