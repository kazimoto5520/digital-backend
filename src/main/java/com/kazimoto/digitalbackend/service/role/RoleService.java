package com.kazimoto.digitalbackend.service.role;

import com.kazimoto.digitalbackend.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role saveRole(Role role);

    Role getSingleRole(Long id);

    void deleteRole(Long id);

}
