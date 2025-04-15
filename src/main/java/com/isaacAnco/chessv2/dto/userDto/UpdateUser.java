package com.isaacAnco.chessv2.dto.userDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {
    @Schema(description = "User's name", example = "miguel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String userName;
    @Schema(description = "User's email", example = "user@example.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String email;
    @Schema(description = "user active", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private boolean isActive;
    @Schema(description = "user draws", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer draws;
    @Schema(description = "user loss", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer loss;
    @Schema(description = "user wins", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer wins;


}
