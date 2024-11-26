package com.bhavya.esdbackend.controller;

import com.bhavya.esdbackend.dto.AlumniEducationRequest;
import com.bhavya.esdbackend.dto.AlumniOrganisationRequest;
import com.bhavya.esdbackend.entity.Alumni;
import com.bhavya.esdbackend.entity.AlumniEducation;
import com.bhavya.esdbackend.entity.Organisation;
import com.bhavya.esdbackend.service.AlumniService;
import com.bhavya.esdbackend.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/alumni")
@RequiredArgsConstructor
public class AlumniController {

    @Autowired
    private final AlumniService alumniService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(alumniService.login(request));
    }

    @PutMapping("/{id}")
    public Alumni updateContactInfo(@PathVariable Long id,@RequestParam String newContactNumber,@RequestParam String newEmail) {
        return alumniService.updateContactInfo(id, newContactNumber,newEmail);
    }

    @PutMapping("/education/{educationId}")
    public ResponseEntity<AlumniEducation> updateEducationQualification(
            @PathVariable Integer educationId,
            @RequestBody AlumniEducationRequest updatedEducation) {

        AlumniEducation updatedAlumniEducation = alumniService.updateEducationQualification(
                educationId,updatedEducation);
        return ResponseEntity.ok(updatedAlumniEducation);
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


}
