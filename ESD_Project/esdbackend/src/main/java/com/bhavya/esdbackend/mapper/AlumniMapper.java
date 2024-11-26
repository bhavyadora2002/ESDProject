package com.bhavya.esdbackend.mapper;

import com.bhavya.esdbackend.entity.Alumni;
import com.bhavya.esdbackend.dto.LoginRequest;
import com.bhavya.esdbackend.entity.AlumniCredentials;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AlumniMapper {
//    public Alumni toEntity(AlumniRequest request) {
//        return Customer.builder()
//                .firstName(request.firstName())
//                .lastName(request.lastName())
//                .email(request.email())
//                .password(request.password())
//                .build();
//    }
    public AlumniCredentials loginEntity(@Valid LoginRequest request) {
        return AlumniCredentials.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}
