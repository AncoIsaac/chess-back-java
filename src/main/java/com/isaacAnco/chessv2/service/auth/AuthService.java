package com.isaacAnco.chessv2.service.auth;

import com.isaacAnco.chessv2.dto.auth.LoginRequestDto;
import com.isaacAnco.chessv2.exceptions.ResourceNotFoundException;
import com.isaacAnco.chessv2.model.user.User;
import com.isaacAnco.chessv2.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid password");
        }

        if (!user.getIsActive()) {
            throw new ResourceNotFoundException("User is not active");
        }

        return user;
    }
}
