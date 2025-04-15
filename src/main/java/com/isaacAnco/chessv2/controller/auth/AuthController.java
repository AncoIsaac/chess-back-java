package com.isaacAnco.chessv2.controller.auth;

import com.isaacAnco.chessv2.dto.auth.LoginRequestDto;
import com.isaacAnco.chessv2.dto.auth.LoginResponseDto;
import com.isaacAnco.chessv2.dto.userDto.UserResponseDto;
import com.isaacAnco.chessv2.exceptions.ResourceNotFoundException;
import com.isaacAnco.chessv2.model.user.User;
import com.isaacAnco.chessv2.response.CustomApiResponse;
import com.isaacAnco.chessv2.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/auth")
@Tag(name = "auth", description = "Auth-realted operations")
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;


    @PostMapping()
    @Operation(summary = "Login user")
    @ApiResponse(responseCode = "201", description = "inicio de seccion correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de usuario inválidos")
    public ResponseEntity<CustomApiResponse> signUp(@RequestBody LoginRequestDto request){
        try {
            User loginUser = authService.signUp(request);
            LoginResponseDto responseDto = convertToResponseDTO(loginUser);
            return ResponseEntity.ok(new CustomApiResponse("success", responseDto, "Login successfully"));
      }catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CustomApiResponse("error", e.getMessage()));
        }
    }

    private LoginResponseDto convertToResponseDTO(User user) {
        return modelMapper.map(user, LoginResponseDto.class);
    }
}
