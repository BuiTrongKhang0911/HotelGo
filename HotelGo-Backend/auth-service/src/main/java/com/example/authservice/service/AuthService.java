package com.example.authservice.service;

import com.example.authservice.dto.AuthRequest;
import com.example.authservice.dto.AuthResponse;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import com.hotelgo.common.exception.AppException;
import com.hotelgo.common.exception.ErrorCode;
import com.hotelgo.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    @Transactional
    public AuthResponse register(AuthRequest request) {
        // Check username or email exists
        if (userRepository.existsByUsername(request.getUsername()) || 
            userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        
        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setVerified(false);
        userRepository.save(user);
        
        // Generate access token
        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        
        // Generate refresh token
        String refreshToken = jwtUtil.generateRefreshToken(user.getId(), user.getUsername());
        
        return new AuthResponse(accessToken, refreshToken, user.getUsername());
    }
}
