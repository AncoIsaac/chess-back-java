package com.isaacAnco.chessv2.dto.userDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String userName;
    private boolean isActive;
    private Number draws;
    private Number loss;
    private Number wins;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
