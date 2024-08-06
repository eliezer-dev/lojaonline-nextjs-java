package dev.eliezer.lojaonline.modules.user.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "user id")
    private Long id;

    @NotBlank(message = "fullname is not provided")
    @Schema(example = "Jose da Silva", requiredMode = Schema.RequiredMode.REQUIRED, description = "user full name")
    private String fullname;

    @NotBlank (message = "email is not provided")
    @Schema(example = "jose@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "user email")
    private String email;

    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "user creation datetime")
    private LocalDateTime createAt;

    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "user update datetime")
    private LocalDateTime updateAt;
}
