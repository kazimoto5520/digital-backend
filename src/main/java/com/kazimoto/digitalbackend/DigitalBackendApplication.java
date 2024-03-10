package com.kazimoto.digitalbackend;

import com.kazimoto.digitalbackend.entity.Permission;
import com.kazimoto.digitalbackend.entity.Role;
import com.kazimoto.digitalbackend.repository.PermissionRepository;
import com.kazimoto.digitalbackend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DigitalBackendApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public static void main(String[] args) {
        SpringApplication.run(DigitalBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Permission permission = new Permission();
//        permission.setName("MANAGE REGIONS");
//        permission.setStatus(1);
//        permissionRepository.save(permission);
//
//        List<Permission> permissions = permissionRepository.findAll();
//
//        Role role = new Role();
//        role.setName("ADMIN");
//        role.setStatus(1);
//        roleRepository.save(role);
//
//        Role role1 = new Role();
//        role.setName("USER");
//        role.setStatus(1);
//        role.setPermissions(permissions);
//        roleRepository.save(role1);
    }
}
