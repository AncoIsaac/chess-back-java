package com.isaacAnco.chessv2.service.auth;

import com.isaacAnco.chessv2.dto.auth.LoginRequestDto;
import com.isaacAnco.chessv2.model.user.User;

public interface IAuthService {
    User signUp(LoginRequestDto userReqDto);
}
