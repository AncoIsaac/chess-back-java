package com.isaacAnco.chessv2.service.user;

import com.isaacAnco.chessv2.dto.userDto.UpdateUser;
import com.isaacAnco.chessv2.dto.userDto.UserRequestDto;
import com.isaacAnco.chessv2.model.user.User;
import org.hibernate.query.Page;

import java.util.List;

public interface IUserService {
    User createUser(UserRequestDto userResDto);
    List<User> getAllUsers();
    User getUserById(String id);
    User updateUser(String id, UpdateUser userDetails);
    void deleteUser(String id);
}
