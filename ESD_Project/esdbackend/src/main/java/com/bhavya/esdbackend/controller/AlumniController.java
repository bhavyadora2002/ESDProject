package com.bhavya.esdbackend.controller;

import com.bhavya.esdbackend.dto.AlumniEducationRequest;
import com.bhavya.esdbackend.dto.AlumniOrganisationRequest;
import com.bhavya.esdbackend.dto.OrganisationRequest;
import com.bhavya.esdbackend.entity.Alumni;
import com.bhavya.esdbackend.entity.AlumniEducation;
import com.bhavya.esdbackend.entity.AlumniOrganisation;
import com.bhavya.esdbackend.entity.Organisation;
import com.bhavya.esdbackend.service.AlumniService;
import com.bhavya.esdbackend.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/alumni")
@RequiredArgsConstructor
public class AlumniController {

    @Autowired
    private final AlumniService alumniService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(alumniService.login(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateContactInfo(@PathVariable Long id,@RequestParam String newContactNumber,
                                                                 @RequestParam String newEmail) {
        Map<String,String> updatedInfo =  alumniService.updateContactInfo(id, newContactNumber,newEmail);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(updatedInfo);    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getAlumniContactInfo(@PathVariable("id") Long id) {
        Map<String, String> contactInfo = alumniService.getContactInfo(id);
//        return ResponseEntity.ok(contactInfo);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(contactInfo);

    }

    @PutMapping("/education/{alumniId}")
    public ResponseEntity<Map<String,Object>> updateEducationQualification(
            @PathVariable Integer alumniId,
            @RequestBody AlumniEducationRequest updatedEducation) {

        Map<String,Object> updatedAlumniEducation = alumniService.updateEducationQualification(
                alumniId,updatedEducation);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(updatedAlumniEducation);
    }

    @GetMapping("/education/{alumniId}")
    public ResponseEntity<Map<String,Object>> getEducationQualification(
            @PathVariable Integer alumniId
            ) {

        Map<String,Object> alumniEducation = alumniService.getEducationQualification(
                alumniId);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(alumniEducation);
    }

    @PostMapping("/org")
    public ResponseEntity<String> createOrganisation(@RequestBody @Valid AlumniOrganisationRequest request) {
        return alumniService.saveAlumniOrganisation(request);    }

    @PutMapping("/org/{id}")
    public ResponseEntity<String> updateOrganisation(@PathVariable Integer id,@RequestBody @Valid AlumniOrganisationRequest request) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid ID: ID must be a positive integer");
        }
        return alumniService.updateAlumniOrganisation(id,request);
    }


    @DeleteMapping("/org/{id}")
    public ResponseEntity<String> deleteOrg(@PathVariable Integer id) {
        return alumniService.deleteOrg(id);
    }
    @GetMapping("/org")
    public List<OrganisationRequest> getOrg() {
        return alumniService.getallOrg();
    }

//    @GetMapping("/education/{alumniId}")
//    public ResponseEntity<Map<String,Object>> getEducationQualification(
//            @PathVariable Integer alumniId
//    ) {
//
//        Map<String,Object> alumniEducation = alumniService.getEducationQualification(
//                alumniId);
//        return ResponseEntity.ok()
//                .header("Content-Type", "application/json")
//                .body(alumniEducation);
//    }


        @GetMapping("/org/{alumniId}")
        public ResponseEntity<List<AlumniOrganisation>> getAlumniOrganisations(@PathVariable Integer alumniId) {
            return alumniService.getAlumniOrganisationsByAlumniId(alumniId);
        }

    @GetMapping("/orgbyorg/{orgId}")
    public ResponseEntity<AlumniOrganisation> getAlumniOrganisationsbyOrg(@PathVariable Integer orgId) {
        return alumniService.getAlumniOrganisationsByOrgId(orgId);
    }
    }





