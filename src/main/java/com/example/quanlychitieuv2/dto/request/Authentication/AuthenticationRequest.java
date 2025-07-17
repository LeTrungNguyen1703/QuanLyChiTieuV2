package com.example.quanlychitieuv2.dto.request.Authentication;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationRequest {
    private String username;
    private String password;

}
