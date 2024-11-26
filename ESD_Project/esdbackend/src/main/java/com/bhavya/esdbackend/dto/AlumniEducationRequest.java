package com.bhavya.esdbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record AlumniEducationRequest(

        @JsonProperty("degree")
        String degree,

        @JsonProperty("passing_year")
        Integer passingYear,

        @JsonProperty("joining_year")
        Integer joiningYear,

        @JsonProperty("college_name")
        String collegeName,

        @JsonProperty("address")
        String address

) {
}
