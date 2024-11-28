package com.bhavya.esdbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumni_education")
public class AlumniEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educationId;

    @ManyToOne
    @JoinColumn(name = "alumni_id", nullable = false)
    @JsonBackReference
    private Alumni alumni;

    @NotNull
    private String degree;

    private Integer passingYear;

    private Integer joiningYear;

    @Column(nullable = false)
    @NotNull
    private String collegeName;

    private String address;

}