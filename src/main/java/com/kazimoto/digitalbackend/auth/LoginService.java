package com.kazimoto.digitalbackend.auth;

import com.kazimoto.digitalbackend.config.JwtService;
import com.kazimoto.digitalbackend.entity.Role;
import com.kazimoto.digitalbackend.entity.User;
import com.kazimoto.digitalbackend.repository.RoleRepository;
import com.kazimoto.digitalbackend.repository.UserRepository;
import com.kazimoto.digitalbackend.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;


    public AuthResponse register(RegisterRequest request){

        List<Role> roles = roleRepository.findAllById(request.getRoleId());

        var user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(1)
                .roles(roles)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getEmail());

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }


    public AuthResponse login(AuthRequest request){
        authenticationManager.authenticate(  new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("No user found by email " + request.getEmail()));

        log.info("The user is {}", user);
        log.info("Role: {}", user.getAuthorities());

        var token = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(request.getEmail());

        log.info("The token is {}", token);

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
}
