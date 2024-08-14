package dev.eliezer.lojaonline.modules.user.dtos;

import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
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
    @Schema(example = "1", description = "user id")
    private Long id;

    @Schema(example = "Jose da Silva", description = "user full name")
    private String fullname;

    @Schema(example = "jose@email.com", description = "user email")
    private String email;

    @Schema(example = "2024-07-21T22:38:10.514664", description = "user creation datetime")
    private LocalDateTime createAt;

    @Schema(example = "2024-07-21T22:38:10.514664", description = "user update datetime")
    private LocalDateTime updateAt;

    @Schema(example = "true", description = "user active")
    private Boolean active;

    public static UserResponseDTO parseUserResponseDTO (UserEntity userEntity) {

        return UserResponseDTO.builder()
                .fullname(userEntity.getFullname())
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .createAt(userEntity.getCreateAt())
                .updateAt(userEntity.getUpdateAt())
                .active(userEntity.getActive())
                .build();
    }

    }
