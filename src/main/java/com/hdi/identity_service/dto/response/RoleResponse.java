package com.hdi.identity_service.dto.response;

import java.util.List;

import com.hdi.identity_service.entity.Permission;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleResponse {
    String name;
    String description;
    List<Permission> permissions;
}
