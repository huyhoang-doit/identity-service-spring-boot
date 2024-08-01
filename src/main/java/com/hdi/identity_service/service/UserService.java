package com.hdi.identity_service.service;

import com.hdi.identity_service.dto.request.UserCreationRequest;
import com.hdi.identity_service.dto.request.UserUpdateRequest;
import com.hdi.identity_service.entity.User;
import com.hdi.identity_service.exception.AppException;
import com.hdi.identity_service.exception.ErrorCode;
import com.hdi.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createRequest(UserCreationRequest request) {
        User user = new User();
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setLastName(request.getFirstName());
        user.setFirstName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(String id) {
       userRepository.deleteById(id);
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setLastName(request.getFirstName());
        user.setFirstName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }
}
