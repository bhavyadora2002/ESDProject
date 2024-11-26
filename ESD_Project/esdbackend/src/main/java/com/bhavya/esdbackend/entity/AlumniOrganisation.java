package com.bhavya.esdbackend.entity;

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
@Table(name = "alumni_organisation")
public class AlumniOrganisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workId;

    @ManyToOne
    @JoinColumn(name = "alumni_id", nullable = false)
    private Alumni alumni;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organisation organisation;


    private String position;

    private java.time.LocalDate joiningDate;

    private java.time.LocalDate leavingDate;
}
