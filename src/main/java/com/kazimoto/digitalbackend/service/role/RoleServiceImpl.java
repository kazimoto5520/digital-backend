package com.kazimoto.digitalbackend.service.role;

import com.kazimoto.digitalbackend.entity.Role;
import com.kazimoto.digitalbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        Role newRole = new Role();
        newRole.setName(role.getName());
        return roleRepository.save(newRole);
    }

    @Override
    public Role getSingleRole(Long id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteRole(Long id) {

    }
}
