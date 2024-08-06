package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.exceptions.EmailFoundException;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.dtos.UserRequestDTO;
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

    public UserResponseDTO execute (UserRequestDTO user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(userSaved -> {
            throw new EmailFoundException(user.getEmail());
        });

        var password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        var UserSaved = userRepository.save(formatUserRequestDTO(user));

        return formatUserEntityToCreateUserResponseDTO(UserSaved);
    }

    UserResponseDTO formatUserEntityToCreateUserResponseDTO (UserEntity userEntity) {
        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .fullname(userEntity.getFullname())
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .createAt(userEntity.getCreateAt())
                .updateAt(userEntity.getUpdateAt())
                .build();
        return userResponseDTO;
    }

    UserEntity formatUserRequestDTO (UserRequestDTO userRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequestDTO.getEmail());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setFullname(userRequestDTO.getFullname());
        return userEntity;
    }
}
