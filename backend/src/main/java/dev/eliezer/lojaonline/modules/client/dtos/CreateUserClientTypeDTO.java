package dev.eliezer.lojaonline.modules.client.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserClientTypeDTO {
    @NotBlank(message = "fullname not provided")
    @Schema(example = "Jose da Silva", requiredMode = Schema.RequiredMode.REQUIRED, description = "user full name")
    private String fullname;

    @NotBlank (message = "email not provided")
    @Email(message = "invalid email")
    @Schema(example = "jose@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "user email")
    private String email;

    @NotBlank (message = "password not provided")
    @Schema(example = "senha1234", requiredMode = Schema.RequiredMode.REQUIRED, description = "user password")
    private String password;

    @JsonIgnore
    private Long userRole = 1L;


}
