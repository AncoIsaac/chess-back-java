package com.isaacAnco.chessv2.service.user;

import com.isaacAnco.chessv2.dto.userDto.UserRequestDto;
import com.isaacAnco.chessv2.model.user.User;

public interface IUserService {
    User createUser(UserRequestDto userResDto);
    User getUserById(String id);
    User updateUser(String id, User userDetails);
    void deleteUser(String id);
}
