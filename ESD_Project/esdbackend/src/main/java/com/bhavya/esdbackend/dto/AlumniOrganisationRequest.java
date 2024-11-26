package com.bhavya.esdbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlumniOrganisationRequest(

        @JsonProperty("alumni_id")
        Integer alumni,

        @JsonProperty("orgnisation_id")
        Integer orgnisation,

        @JsonProperty("position")
        String position,

        @JsonProperty("joining_date")
        java.time.LocalDate joiningDate,

        @JsonProperty("leaving_date")
        java.time.LocalDate leavingDate

) {
}
