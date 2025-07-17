package com.example.quanlychitieuv2.service;


import com.example.quanlychitieuv2.dto.request.Authentication.AuthenticationRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.IntrospectRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.LogoutRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    com.example.quanlychitieuv2.dto.response.Authentication.AuthenticationResponse authenticate(AuthenticationRequest request);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
}
