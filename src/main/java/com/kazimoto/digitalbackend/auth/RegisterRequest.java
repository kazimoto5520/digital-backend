package com.kazimoto.digitalbackend.auth;

import com.kazimoto.digitalbackend.entity.District;
import com.kazimoto.digitalbackend.entity.Region;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegisterRequest {
    @NotEmpty(message = "full name should not be empty")
    private String fullName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 12, max = 12)
    private String phone;

//    @NotEmpty
    private String tinNumber;

//    @NotEmpty
    private String domainUrl;

    private String address;

    private String imgUrl;

    private Region region;

    private District district;

    private Integer status = 1;

    private List<@Valid @NotEmpty String> roles;

}
