package com.bhavya.esdbackend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "organisations")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organisationId;

    @Column(nullable = false, unique = true)
    private String name;

    @Lob
    private String address;

    @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
    @JsonBackReference
    private java.util.List<AlumniOrganisation> alumniOrganisations;

}


