package com.kazimoto.digitalbackend.service.permission;

import com.kazimoto.digitalbackend.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getAllPermissions();
    Permission getSinglePermission(Long id);
    Permission savePermission(Permission permission);
    void deletePermission(Long id);
}
