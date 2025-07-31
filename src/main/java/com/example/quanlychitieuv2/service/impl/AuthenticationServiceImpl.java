package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.Authentication.AuthenticationRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.IntrospectRequest;
import com.example.quanlychitieuv2.dto.request.Authentication.LogoutRequest;
import com.example.quanlychitieuv2.dto.response.Authentication.AuthenticationResponse;
import com.example.quanlychitieuv2.dto.response.Authentication.IntrospectResponse;
import com.example.quanlychitieuv2.entity.InvalidatedToken;
import com.example.quanlychitieuv2.entity.SoHu;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.enums.ErrorCode;
import com.example.quanlychitieuv2.exception.AppException;
import com.example.quanlychitieuv2.exception.ResourceNotFound;
import com.example.quanlychitieuv2.repository.InvalidatedTokenRepository;
import com.example.quanlychitieuv2.repository.UserRepository;
import com.example.quanlychitieuv2.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SECRET_KEY;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {

        User user = this.getUserByUsername(request.getUsername());

        this.checkPassword(user, request.getPassword());

        String token = this.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(passwordEncoder.matches(request.getPassword(), user.getNdMatkhau()))
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();

        boolean isValidToken = true;

        try {
            this.verifyToken(token);
        } catch (AppException e) {
            isValidToken = false;
        }

        return IntrospectResponse.builder()
                .valid(isValidToken)
                .build();
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

        boolean verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED.getMessage());
        }

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED.getMessage());
        }

        return signedJWT;
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = this.verifyToken(request.getToken());

        String jwtId = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jwtId)
                .expiryTime(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getNdTen())
                .issuer("nguyendev")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1000, ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Failed to sign token {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Xây dựng phạm vi quyền cho người dùng dựa trên các vai trò của họ
     *
     * @param user Người dùng cần xây dựng phạm vi quyền
     * @return Chuỗi chứa danh sách quyền của người dùng
     */
    private String buildScope(User user) {
        // Lấy danh sách các bản ghi SoHu mà người dùng là chủ ví
        return user.getSoHusDuocCap().stream()
                .filter(soHu -> soHu.getUserCapQuyenId() == null) // Người dùng là chủ ví
                .map(SoHu::getRole)
                .distinct()
                .flatMap(role -> Stream.concat(
                        Stream.of("ROLE_" + role.getName()),
                        role.getPermissions().stream() // Sử dụng trực tiếp danh sách quyền từ Role
                ))
                .collect(Collectors.joining(" "));
    }

    private User getUserByUsername(String username) {
        return userRepository.findByNdTen(username)
                .orElseThrow(() -> new ResourceNotFound("User not found with username: " + username));
    }

    private void checkPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getNdMatkhau())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH.getMessage());
        }

    }

}
