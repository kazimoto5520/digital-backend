//package com.kazimoto.digitalbackend.service;
//
//import com.kazimoto.digitalbackend.entity.RefreshToken;
//import com.kazimoto.digitalbackend.repository.RefreshTokenRepository;
//import com.kazimoto.digitalbackend.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class RefreshTokenService {
//
//    private final UserRepository userRepository;
//    private final RefreshTokenRepository tokenRepository;
//
//    public RefreshToken createRefreshToken(String username){
//        var user = userRepository.findByEmail(username)
//                .orElseThrow(()-> new UsernameNotFoundException("No user found with email " + username));
//        RefreshToken refreshToken = user.getRefreshToken();
//
//        if (refreshToken == null){
//            refreshToken = RefreshToken.builder()
//                    .refreshToken(UUID.randomUUID().toString())
//                    .expirationTime(Instant.now().plusMillis(5*60*60*10000))
//                    .user(user)
//                    .build();
//
//            tokenRepository.save(refreshToken);
//        }
//
//        return refreshToken;
//    }
//
//    public RefreshToken verifyRefreshToken(String refreshToken){
//        RefreshToken refToken = tokenRepository.findByRefreshToken(refreshToken)
//                .orElseThrow(()-> new RuntimeException("Token not found"));
//
//        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0){
//            tokenRepository.delete(refToken);
//            throw new RuntimeException("Refresh Token expired");
//        }
//
//        return refToken;
//    }
//
//}
