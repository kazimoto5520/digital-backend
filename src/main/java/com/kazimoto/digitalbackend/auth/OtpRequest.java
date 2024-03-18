package com.kazimoto.digitalbackend.auth;

import lombok.Data;

@Data
public class OtpRequest {
    private String email;
    private String otp;
}
