package com.hdi.identity_service.service;

import com.hdi.identity_service.dto.request.PermissionRequest;
import com.hdi.identity_service.dto.response.PermissionResponse;
import com.hdi.identity_service.entity.Permission;
import com.hdi.identity_service.mapper.PermissionMapper;
import com.hdi.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);

        permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> findAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permission -> permissionMapper.toPermissionResponse(permission)).toList();
    }

    public void delete(String name) {
        permissionRepository.deleteById(name);
    }
}
