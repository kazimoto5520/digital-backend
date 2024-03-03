package com.kazimoto.digitalbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="permision_role",joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
    },inverseJoinColumns = {@JoinColumn (name="permission_id", referencedColumnName = "id")})
    private List<Permission> permissions;

}
