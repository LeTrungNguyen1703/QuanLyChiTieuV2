package com.example.quanlychitieuv2.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    NOT_FOUND(1, "Entity not found", HttpStatus.NOT_FOUND),
    PASSWORD_NOT_MATCH(2, "Password does not match", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(3, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(4, "Unauthorized", HttpStatus.FORBIDDEN),
    AUTHENTICATION_FAILED(5, "Authentication failed", HttpStatus.UNAUTHORIZED),
    USERNAME_EXISTS(6, "Username already exists", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(7, "Role not found", HttpStatus.BAD_REQUEST),
    ;

    Integer code;
    String message;
    HttpStatusCode httpStatusCode;

}
