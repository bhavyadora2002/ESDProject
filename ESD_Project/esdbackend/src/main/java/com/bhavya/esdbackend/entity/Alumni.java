package com.bhavya.esdbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumni")
public class Alumni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alumniId;

    @Column(nullable = false, unique = true)
    private String email;

    private String contactNumber;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    @JsonIgnoreProperties
    private Student student;

    @OneToOne(mappedBy = "alumni", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private AlumniCredentials alumniCredentials;

    @OneToMany(mappedBy = "alumni", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private List<AlumniEducation> educationDetails;


    @OneToMany(mappedBy = "alumni", cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private java.util.List<AlumniOrganisation> workHistory;

}
