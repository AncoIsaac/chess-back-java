package com.isaacAnco.chessv2.controller.user;

import com.isaacAnco.chessv2.dto.userDto.UserRequestDto;
import com.isaacAnco.chessv2.dto.userDto.UserResponseDto;
import com.isaacAnco.chessv2.exceptions.ResourceNotFoundException;
import com.isaacAnco.chessv2.model.user.User;
import com.isaacAnco.chessv2.response.CustomApiResponse;
import com.isaacAnco.chessv2.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
@Tag(name = "Users", description = "User-realted operations")
public class UserController {
    private final UserService userService;

    @PostMapping()
    @Operation(summary = "Crear un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos")
    public ResponseEntity<CustomApiResponse> createUser(@RequestBody UserRequestDto user){
        try {
            User newUser = userService.createUser(user);
            UserResponseDto responseDto = convertToResponseDTO(newUser);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new CustomApiResponse("success", responseDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CustomApiResponse("error", e.getMessage()));
        }
    }
    private UserResponseDto convertToResponseDTO(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUserName(user.getUserName());
        dto.setActive(user.getIsActive());  // Assuming the User entity has isActive()
        dto.setDraws(user.getDraws());   // Assuming these fields exist in User
        dto.setLoss(user.getLoss());
        dto.setWins(user.getWins());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }
}
