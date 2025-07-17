package com.example.quanlychitieuv2.dto.response.Authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntrospectResponse {

    boolean valid;
    String subject;
    String issuer;
    Date expirationTime;
}
