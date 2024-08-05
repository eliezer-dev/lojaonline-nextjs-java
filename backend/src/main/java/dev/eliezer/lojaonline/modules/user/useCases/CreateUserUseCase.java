package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.exceptions.EmailFoundException;
import dev.eliezer.lojaonline.exceptions.UserFoundException;
import dev.eliezer.lojaonline.modules.user.dtos.CreateUserResponseDTO;
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

    public CreateUserResponseDTO execute (UserRequestDTO user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(userSaved -> {
            throw new EmailFoundException(user.getEmail());
        });

        var password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        var UserSaved = userRepository.save(formatUserRequestDTO(user));

        return formatUserEntityToCreateUserResponseDTO(UserSaved);
    }

    CreateUserResponseDTO formatUserEntityToCreateUserResponseDTO (UserEntity userEntity) {
        CreateUserResponseDTO createUserResponseDTO = CreateUserResponseDTO.builder()
                .fullname(userEntity.getFullname())
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .createAt(userEntity.getCreateAt())
                .updateAt(userEntity.getUpdateAt())
                .build();
        return createUserResponseDTO;
    }

    UserEntity formatUserRequestDTO (UserRequestDTO userRequestDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequestDTO.getEmail());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setFullname(userRequestDTO.getFullname());
        return userEntity;
    }
}
