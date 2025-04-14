package com.isaacAnco.chessv2.service.user;

import com.isaacAnco.chessv2.dto.userDto.UserRequestDto;
import com.isaacAnco.chessv2.exceptions.ResourceNotFoundException;
import com.isaacAnco.chessv2.model.user.User;
import com.isaacAnco.chessv2.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRequestDto request) {
        if (userRepository.existsByEmail (request.getEmail())){
            throw new ResourceNotFoundException("the email is already registered ");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public User updateUser(String id, User userDetails) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }
}
