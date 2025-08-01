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
import com.example.quanlychitieuv2.util.FindBy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserSeviceImpl extends AbstractBaseService<UserRequest, UserResponse, User,Integer> {

    UserMapper userMapper;
    FindBy findBy;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Autowired
    public UserSeviceImpl(UserRepository userRepository, UserMapper userMapper, FindBy findBy, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        super(userRepository);
        this.userMapper = userMapper;
        this.findBy = findBy;
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

        User user = userMapper.toEntity(userRequest);
        user.setNdMatkhau(passwordEncode);

        // Lưu user trước để có ID
        user = userRepository.save(user);

        log.info("Creating userId: {} and userName {} ", user.getId(), user.getNdTen());

        // Lưu ý: Không cần thiết lập quyền cho user mới tạo ở đây
        // Quyền sẽ được thiết lập khi người dùng tạo hoặc được cấp quyền với ví tiền thông qua bảng SoHu

        return userMapper.toRes(user);
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
