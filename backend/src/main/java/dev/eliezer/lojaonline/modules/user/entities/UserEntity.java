package dev.eliezer.lojaonline.modules.user.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eliezer.lojaonline.modules.image.entities.ImageEntity;
import dev.eliezer.lojaonline.modules.user.dtos.CreateUserRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

@Data
@Entity(name = "tb_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "user id")
    private Long id;

    @NotBlank (message = "fullname not provided")
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

    @JsonIgnore
    @Column(columnDefinition = "boolean default true")
    @Schema(example = "true", description = "user active")
    private Boolean active = true;

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "user creation datetime")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "user update datetime")
    private LocalDateTime updateAt;

    @Column(name = "id_picture")
    private Long idPicture;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_image", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private ImageEntity imageEntity;
    
    public static UserEntity parseUserEntity (CreateUserRequestDTO createUserRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(createUserRequestDTO.getEmail());
        userEntity.setPassword(createUserRequestDTO.getPassword());
        userEntity.setFullname(createUserRequestDTO.getFullname());
        return userEntity;
    }

}
