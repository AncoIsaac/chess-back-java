package com.isaacAnco.chessv2.service.user;

import com.isaacAnco.chessv2.dto.userDto.UpdateUser;
import com.isaacAnco.chessv2.dto.userDto.UserRequestDto;
import com.isaacAnco.chessv2.exceptions.ResourceNotFoundException;
import com.isaacAnco.chessv2.model.user.User;
import com.isaacAnco.chessv2.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        user.setUserName(request.getUserName());

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User updateUser(String id, UpdateUser userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (userDetails.getEmail() != null) {
            if (userRepository.existsByEmail(userDetails.getEmail())) {
                throw new ResourceNotFoundException("the email is already registered ");
            }
            existingUser.setEmail(userDetails.getEmail());
        }

        existingUser.setIsActive(userDetails.isActive());

        if (userDetails.getUserName() != null){
            existingUser.setUserName(userDetails.getUserName());
        }

        if (userDetails.getDraws() != null){
            existingUser.setDraws(userDetails.getDraws());
        }

        if (userDetails.getWins() != null){
            existingUser.setWins(userDetails.getWins());
        }

        if (userDetails.getLoss() != null){
            existingUser.setLosses(userDetails.getLoss());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(existingUser);
    }
}
