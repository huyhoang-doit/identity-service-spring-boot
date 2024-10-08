package com.hdi.identity_service.service;

import com.hdi.identity_service.dto.request.RoleRequest;
import com.hdi.identity_service.dto.response.RoleResponse;
import com.hdi.identity_service.entity.Role;
import com.hdi.identity_service.mapper.RoleMapper;
import com.hdi.identity_service.repository.PermissionRepository;
import com.hdi.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;


    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> roleMapper.toRoleResponse(role)).toList();
    }

    public void delete(String name) {
        roleRepository.deleteById(name);
    }

}
