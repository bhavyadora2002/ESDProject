package com.bhavya.esdbackend.service;


import com.bhavya.esdbackend.dto.AlumniEducationRequest;
import com.bhavya.esdbackend.dto.AlumniOrganisationRequest;
import com.bhavya.esdbackend.dto.LoginRequest;

import com.bhavya.esdbackend.dto.OrganisationRequest;
import com.bhavya.esdbackend.entity.*;
import com.bhavya.esdbackend.exception.ResourceNotFoundException;
import com.bhavya.esdbackend.helper.EncryptionService;
import com.bhavya.esdbackend.helper.JWTHelper;
import com.bhavya.esdbackend.mapper.AlumniMapper;
import com.bhavya.esdbackend.mapper.EducationMapper;

import com.bhavya.esdbackend.mapper.OrganisationMapper;
import com.bhavya.esdbackend.repo.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Autowired
    private OrganisationRepo organisationRepo;


    public Map<String,Object> login(LoginRequest request) {
        AlumniCredentials r = mapper.loginEntity(request);
        String email = r.getEmail();
        String password = r.getPassword();
        AlumniCredentials cred = alumniCredRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("No credentials found for email: " + request.email()));


        if(!encryptionService.validates(password, cred.getPassword())) {
            throw new IllegalArgumentException("Wrong Password or Email");
        }
        Alumni a = alumniRepo.getAlumniByEmail(email);
        System.out.println(a.getAlumniId());
        return Map.of(
                "token", jwtHelper.generateToken(request.email()),
                "id", a.getAlumniId()
        );

    }

    public Map<String, String> getContactInfo(Long id) {
        Alumni alumni = alumniRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumni not found with Id: " + id));
        return Map.of(
                "email", alumni.getEmail(),
                "contactNumber", alumni.getContactNumber()
        );
    }

    public Map<String, String> updateContactInfo(Long id, String newContactNumber, String newEmail) {
        System.out.println("updating email");
        Alumni alumni = alumniRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumni not found with Id: " + id));

            System.out.println(newEmail);
            alumni.setEmail(newEmail);
            System.out.println("updating email");
            alumni.setContactNumber(newContactNumber);
        alumniRepo.save(alumni);
        return Map.of(
                "email", alumni.getEmail(),
                "contactNumber", alumni.getContactNumber()
        );
    }

    public Map<String, Object> getEducationQualification(Integer alumniId) {

        AlumniEducation e = alumniEducationRepository.findByAlumni_AlumniId(alumniId);

        return Map.of(
                "degree", e.getDegree(),
                "passing_year", e.getPassingYear(),
                "joining_year",e.getJoiningYear(),
                "college_name", e.getCollegeName(),
                "address",e.getAddress()
        );
    }

    public Map<String, Object> updateEducationQualification(Integer alumniId, AlumniEducationRequest request ) {
        AlumniEducation e = alumniEducationRepository.findByAlumni_AlumniId(alumniId);
        Integer educationId = e.getEducationId();
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
        alumniEducationRepository.save(alumniEducation);
         return Map.of(
                "degree", alumniEducation.getDegree(),
                "passing_year", alumniEducation.getPassingYear(),
                 "joining_year",alumniEducation.getJoiningYear(),
                 "college_name", alumniEducation.getCollegeName(),
                 "address",alumniEducation.getAddress()
        );
    }

    public ResponseEntity<String> saveAlumniOrganisation(@Valid AlumniOrganisationRequest request) {
        AlumniOrganisation r = orgmapper.orgEntity(request);
        alumniOrgRepo.save(r);
        return ResponseEntity.ok("Organisation created successfully");
    }

    public ResponseEntity<String> updateAlumniOrganisation(Integer id, @Valid AlumniOrganisationRequest request) {
        AlumniOrganisation alumniOrganisation = alumniOrgRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid work ID"));
        AlumniOrganisation r = orgmapper.orgEntity(request);
        if(r.getOrganisation()!=null){
            alumniOrganisation.setOrganisation(r.getOrganisation());
        }
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


    public ResponseEntity<List<AlumniOrganisation>> getAlumniOrganisationsByAlumniId(Integer alumniId) {
        List<AlumniOrganisation> l = alumniOrgRepo.findByAlumni_AlumniId(alumniId);
        return ResponseEntity.ok(l);
    }

    public List<OrganisationRequest> getallOrg() {
        List<Organisation> organisations = organisationRepo.findAll();
        return organisations.stream()
                .map(org -> new OrganisationRequest(org.getOrganisationId(), org.getName())) // Mapping to DTO
                .collect(Collectors.toList());
    }

    public ResponseEntity<AlumniOrganisation> getAlumniOrganisationsByOrgId(Integer orgId) {
        AlumniOrganisation al = alumniOrgRepo.findById(orgId).orElseThrow(() -> new IllegalArgumentException("Invalid education ID"));;
        return ResponseEntity.ok(al);
    }
}
