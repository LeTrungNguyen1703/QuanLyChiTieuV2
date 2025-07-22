package com.example.quanlychitieuv2.controller.impl;

import com.example.quanlychitieuv2.controller.AuthenticationController;
import com.example.quanlychitieuv2.dto.ApiResponseError;
import com.example.quanlychitieuv2.dto.ApiResponseSuccess;
import com.example.quanlychitieuv2.dto.request.Authentication.AuthenticationRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.IntrospectRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.LogoutRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.AuthenticationResponse;
import com.example.quanlychitieuv2.dto.response.Authentication.IntrospectResponse;
import com.example.quanlychitieuv2.enums.ErrorCode;
import com.example.quanlychitieuv2.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationControllerImpl implements AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponseSuccess<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws AuthenticationException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        if (authenticationResponse.isAuthenticated()) {
            return new ApiResponseSuccess<>(authenticationResponse);
        } else {
            return new ApiResponseError(ErrorCode.AUTHENTICATION_FAILED.getMessage(), ErrorCode.AUTHENTICATION_FAILED.getCode());
        }
    }

    @PostMapping("/introspect")
    public ApiResponseSuccess<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {

        IntrospectResponse result = authenticationService.introspect(request);

        return new ApiResponseSuccess<>(result);
    }

    @PostMapping("/logout")
    public ApiResponseSuccess<String> logout( @RequestBody LogoutRequest request) throws ParseException, JOSEException {

        authenticationService.logout(request);
        return new ApiResponseSuccess<>(null);
    }
}
