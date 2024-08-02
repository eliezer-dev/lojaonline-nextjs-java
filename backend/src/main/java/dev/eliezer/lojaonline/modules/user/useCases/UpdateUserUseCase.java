package dev.eliezer.lojaonline.modules.user.useCases;


import dev.eliezer.lojaonline.exceptions.BusinessException;
import dev.eliezer.lojaonline.exceptions.EmailFoundException;
import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.dtos.CreateUserResponseDTO;
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

    public CreateUserResponseDTO execute (UserEntity user) {
        if (user.getId() == null) {
            throw new BusinessException("id is not provided.");
        }

        UserEntity userToUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(user.getId()));

        if (user.getEmail() != null && !user.getEmail().isBlank()){
            userRepository.findByEmail(user.getEmail()).ifPresent((userFound) -> {
                if (!userFound.getId().equals(user.getId())) {
                    throw new EmailFoundException(user.getEmail());
                }
            });
            userToUpdate.setEmail(user.getEmail());
        }

        userToUpdate.setFullname(user.getFullname() != null && !user.getFullname().isBlank() ?
                user.getFullname() : userToUpdate.getFullname());

        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            var password = passwordEncoder.encode(user.getPassword());
            userToUpdate.setPassword(password);
        }

        UserEntity userUpdated = userRepository.save(userToUpdate);
        return formatUserEntityToCreateUserResponseDTO(userUpdated);
    }

    CreateUserResponseDTO formatUserEntityToCreateUserResponseDTO (UserEntity userEntity) {
        return CreateUserResponseDTO.builder()
                .fullname(userEntity.getFullname())
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .createAt(userEntity.getCreateAt())
                .updateAt(userEntity.getUpdateAt())
                .build();
    }

}
