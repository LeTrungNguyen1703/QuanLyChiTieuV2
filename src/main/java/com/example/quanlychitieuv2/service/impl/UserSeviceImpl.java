package com.example.quanlychitieuv2.service.impl;

import com.example.quanlychitieuv2.dto.request.UserRequest;
import com.example.quanlychitieuv2.dto.response.UserResponse;
import com.example.quanlychitieuv2.entity.Role;
import com.example.quanlychitieuv2.entity.User;
import com.example.quanlychitieuv2.enums.ErrorCode;
import com.example.quanlychitieuv2.exception.AuthenticatedException;
import com.example.quanlychitieuv2.mapper.BaseMapper;
import com.example.quanlychitieuv2.mapper.impl.UserMapper;
import com.example.quanlychitieuv2.repository.RoleRepository;
import com.example.quanlychitieuv2.repository.UserRepository;
import com.example.quanlychitieuv2.service.AbstractBaseService;
import com.example.quanlychitieuv2.util.FindById;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSeviceImpl extends AbstractBaseService<UserRequest, UserResponse, User,Integer> {

    UserMapper userMapper;
    FindById findById;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Autowired
    public UserSeviceImpl(UserRepository userRepository, UserMapper userMapper, FindById findById, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        super(userRepository);
        this.userMapper = userMapper;
        this.findById = findById;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    protected BaseMapper<UserRequest, UserResponse, User> getMapper() {
        return userMapper;
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        this.checkUserExists(userRequest);

        String passwordEncode = passwordEncoder.encode(userRequest.getNdMatkhau());

        List<Role> roles = this.getRolesFromRequest(userRequest);

        User user = userMapper.toEntity(userRequest);
        user.setNdMatkhau(passwordEncode);
        user.setDanhSachQuyen(new HashSet<>(roles));
        user =  userRepository.save(user);
        log.info("Creating userId: {} and userName {} ", user.getId(),user.getNdTen());
        return userMapper.toRes(user);
    }



    private List<Role> getRolesFromRequest(UserRequest userRequest) {
        List<Role> roles = roleRepository.findAllById(userRequest.getDanhSachQuyen());
        if (roles.isEmpty()) {
            Role role = findById.findRoleById("ROLE_USER"); // Default role if none provided
            roles.add(role);
        }

        log.info("Roles for user {}: {}", userRequest.getNdTen(), roles);

        return roles;
    }

    private void checkUserExists(UserRequest userRequest) {
        log.info("Creating user with request: {}", userRequest);

        boolean userExists = userRepository.existsByNdTen(userRequest.getNdTen());

        if (userExists) {
            log.error("User {} already exists", userRequest.getNdTen());
            throw new AuthenticatedException(ErrorCode.USERNAME_EXISTS.getMessage());
        }
    }
}
