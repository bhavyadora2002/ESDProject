package com.bhavya.esdbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer studentId;

        @Column(nullable = false, unique = true)
        private String rollNumber;

        @Column(nullable = false)
        private String firstName;

        private String lastName;

        @Column(nullable = false, unique = true)
        private String email;

        private String photographPath;

        private Double cgpa;

        private Integer totalCredits;

        private Integer graduationYear;

        private Integer domain;

        private Integer specialisationId;

        private Integer placementId;

//        @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
//        @JsonIgnoreProperties
//        private Alumni alumni;


    }



