package com.isaacAnco.chessv2.controller.user;

import com.isaacAnco.chessv2.dto.userDto.UpdateUser;
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
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
@Tag(name = "Users", description = "User-realted operations")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

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
                    .body(new CustomApiResponse("success", responseDto, "User created successfully"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CustomApiResponse("error", e.getMessage()));
        }
    }

    @GetMapping("allUser")
    @Operation(summary = "Obtener todos los usuarios ")
    public ResponseEntity<CustomApiResponse> getAllUsers() {
        if (userService.getAllUsers().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomApiResponse("error", "No users found"));
        }
        UserResponseDto[] responseDtos = userService.getAllUsers()
                .stream()
                .map(this::convertToResponseDTO)
                .toArray(UserResponseDto[]::new);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomApiResponse("success", responseDtos));
    }

    @GetMapping("getOnlyUser/{id}")
    @Operation(summary = "Obtener un usuario por su id")
    public ResponseEntity<CustomApiResponse> getUserById(@PathVariable String id) {

        try {
            User user = userService.getUserById(id);
            UserResponseDto responseDto = convertToResponseDTO(user);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomApiResponse("success", responseDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomApiResponse("error", e.getMessage()));
        }
    }

    @PatchMapping("updateUser/{id}")
    @Operation(summary = "Actualizar un usuario por su id")
    public ResponseEntity<CustomApiResponse> updateUser(@PathVariable String id, @RequestBody UpdateUser userDetails) {
        try {
            User user = userService.updateUser(id, userDetails);
            UserResponseDto responseDto = convertToResponseDTO(user);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomApiResponse("success", responseDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomApiResponse("error", e.getMessage()));
        }
    }

    private UserResponseDto convertToResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }
}