package com.hdi.identity_service.service;

import com.hdi.identity_service.dto.request.UserCreationRequest;
import com.hdi.identity_service.dto.request.UserUpdateRequest;
import com.hdi.identity_service.dto.response.UserResponse;
import com.hdi.identity_service.entity.User;
import com.hdi.identity_service.enums.Role;
import com.hdi.identity_service.exception.AppException;
import com.hdi.identity_service.exception.ErrorCode;
import com.hdi.identity_service.mapper.UserMapper;
import com.hdi.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createRequest(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public void deleteUser(String id) {
       userRepository.deleteById(id);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user, request);
       return  userMapper.toUserResponse(userRepository.save(user));
    }
}
