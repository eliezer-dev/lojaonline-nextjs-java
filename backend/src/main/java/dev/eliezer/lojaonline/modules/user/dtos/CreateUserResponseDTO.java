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
public class CreateUserResponseDTO {
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "id of user")
    private Long id;

    @NotBlank(message = "fullname is not provided")
    @Schema(example = "Jose da Silva", requiredMode = Schema.RequiredMode.REQUIRED, description = "full name of user")
    private String fullname;

    @NotBlank (message = "email is not provided")
    @Schema(example = "jose@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "e-mail of user")
    private String email;

    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "creation time of user")
    private LocalDateTime createAt;

    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "update time of user")
    private LocalDateTime updateAt;
}
