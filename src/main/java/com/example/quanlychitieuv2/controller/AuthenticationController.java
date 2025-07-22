package com.example.quanlychitieuv2.controller;

import com.example.quanlychitieuv2.dto.ApiResponseSuccess;
import com.example.quanlychitieuv2.dto.request.Authentication.AuthenticationRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.IntrospectRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.LogoutRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.AuthenticationResponse;
import com.example.quanlychitieuv2.dto.response.Authentication.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import org.apache.tomcat.websocket.AuthenticationException;

import java.text.ParseException;


public interface AuthenticationController {
    ApiResponseSuccess<AuthenticationResponse> authenticate(AuthenticationRequest request) throws AuthenticationException;

    ApiResponseSuccess<IntrospectResponse> introspect(IntrospectRequest request) throws ParseException, JOSEException;

    ApiResponseSuccess<String> logout(LogoutRequest request) throws ParseException, JOSEException;
}
