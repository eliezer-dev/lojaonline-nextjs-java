package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.exceptions.UserFoundException;
import dev.eliezer.lojaonline.modules.dtos.CreateUserResponseDTO;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateUserResponseDTO execute (UserEntity user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(userSaved -> {
            throw new UserFoundException();
        });


        var password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        var UserSaved = userRepository.save(user);

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
}
