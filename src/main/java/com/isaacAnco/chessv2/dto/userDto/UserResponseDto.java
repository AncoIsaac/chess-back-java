package com.isaacAnco.chessv2.dto.userDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String email;
    private String userName;
    private boolean isActive;
    private Integer draws;
    private Integer loss;
    private Integer wins;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
