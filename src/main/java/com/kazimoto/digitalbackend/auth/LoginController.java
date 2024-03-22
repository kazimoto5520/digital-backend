package com.kazimoto.digitalbackend.auth;

import com.kazimoto.digitalbackend.config.JwtService;
import com.kazimoto.digitalbackend.entity.RefreshToken;
import com.kazimoto.digitalbackend.entity.User;
import com.kazimoto.digitalbackend.repository.UserRepository;
//import com.kazimoto.digitalbackend.service.RefreshTokenService;
import com.kazimoto.digitalbackend.service.otp.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private final JwtService jwtService;
//    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final OtpService otpService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request, Authentication auth) {
        log.info("Request: {}", request);
        try {
            return ResponseEntity.ok(loginService.login(request));
        } catch (Exception e) {
            log.error("Error occurred during login", e);
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        log.info("Request: {}", request);
        try {
            return ResponseEntity.ok(loginService.register(request));
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
//        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
//        User user = refreshToken.getUser();
//
//        String accessToken = jwtService.generateToken(user);
//        return ResponseEntity.ok(AuthResponse.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken.getRefreshToken())
//                .build());
//    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (otpService.verifyOTP(user.getEmail(), request.getOtp())){
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(AuthResponse.builder()
                    .accessToken(token)
                    .build());
        }else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "INVALID OTP");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
