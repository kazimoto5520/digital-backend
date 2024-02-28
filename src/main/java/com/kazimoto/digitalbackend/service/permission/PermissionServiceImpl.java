package com.kazimoto.digitalbackend.service.permission;

import com.kazimoto.digitalbackend.entity.Permission;
import com.kazimoto.digitalbackend.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService{
    private final PermissionRepository permissionRepository;


    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getSinglePermission(Long id) {
        return permissionRepository.findById(id).orElseThrow();
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
