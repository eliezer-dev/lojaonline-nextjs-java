package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.exceptions.EmailFoundException;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.dtos.CreateUserRequestDTO;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO execute (CreateUserRequestDTO user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(userSaved -> {
            throw new EmailFoundException(user.getEmail());
        });

        var password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        UserEntity UserSaved = userRepository.save(formatCreateUserRequestDTOToUserEntity(user));

        return formatUserEntityToUserResponseDTO(UserSaved);
    }

    UserResponseDTO formatUserEntityToUserResponseDTO (UserEntity userEntity) {
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .fullname(userEntity.getFullname())
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .createAt(userEntity.getCreateAt())
                .updateAt(userEntity.getUpdateAt())
                .active(userEntity.getActive())
                .build();
        return userResponseDTO;
    }

    UserEntity formatCreateUserRequestDTOToUserEntity (CreateUserRequestDTO createUserRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(createUserRequestDTO.getEmail());
        userEntity.setPassword(createUserRequestDTO.getPassword());
        userEntity.setFullname(createUserRequestDTO.getFullname());
        return userEntity;
    }
}
