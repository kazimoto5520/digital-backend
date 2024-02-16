package com.kazimoto.digitalbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Company name should not be empty")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 12, max = 12)
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "Email should not be empty")
    @Email
    @Column(name = "email" ,unique = true)
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 6, max = 25)
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "tin_number")
    private String tinNumber;

    @NotEmpty
    @Column(name = "domain_url")
    private String domainUrl;

    @Column(name = "address")
    private String address;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToMany
    @JoinTable(
            name = "company_customers",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> customers = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Column(name = "status")
    private Integer status = 1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
