package dev.eliezer.lojaonline.modules.user.useCases;


import dev.eliezer.lojaonline.exceptions.EmailFoundException;
import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.user.dtos.UpdateUserRequestDTO;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.dtos.CreateUserRequestDTO;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO execute (UpdateUserRequestDTO user, Long userId) {

        UserEntity userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(userId));

        if (user.getEmail() != null && !user.getEmail().isBlank()) {
           Optional<UserEntity> userFound = userRepository.findByEmail(user.getEmail());

           if (userFound.isPresent() && !userFound.get().getId().equals(userId)) {
               throw new EmailFoundException(user.getEmail());
           }
           userToUpdate.setEmail(user.getEmail());
        }

        userToUpdate.setFullname(user.getFullname() != null && !user.getFullname().isBlank() ?
                user.getFullname() : userToUpdate.getFullname());

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            var password = passwordEncoder.encode(user.getPassword());
            userToUpdate.setPassword(password);
        }

        return formatUserEntityToCreateUserResponseDTO(userRepository.save(userToUpdate), userId);
    }

    UserResponseDTO formatUserEntityToCreateUserResponseDTO (UserEntity userEntity, Long userId) {
        return UserResponseDTO.builder()
                .fullname(userEntity.getFullname())
                .id(userId)
                .email(userEntity.getEmail())
                .createAt(userEntity.getCreateAt())
                .updateAt(userEntity.getUpdateAt())
                .active(userEntity.getActive())
                .build();
    }

}
