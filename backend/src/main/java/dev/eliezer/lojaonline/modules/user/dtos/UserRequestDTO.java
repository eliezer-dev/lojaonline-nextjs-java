package dev.eliezer.lojaonline.modules.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(message = "fullname not provided")
    @Column(nullable = false)
    @Schema(example = "Jose da Silva", requiredMode = Schema.RequiredMode.REQUIRED, description = "user full name")
    private String fullname;

    @NotBlank (message = "email not provided")
    @Column(nullable = false, unique = true)
    @Schema(example = "jose@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "user email")
    private String email;

    @NotBlank (message = "password not provided")
    @Column(nullable = false)
    @Schema(example = "senha1234", requiredMode = Schema.RequiredMode.REQUIRED, description = "user password")
    private String password;

}
