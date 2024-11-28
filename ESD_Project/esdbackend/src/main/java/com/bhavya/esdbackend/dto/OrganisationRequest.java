package com.bhavya.esdbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrganisationRequest {
    private Integer id;
    private String name;
}