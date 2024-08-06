package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListAllUsersUseCase {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> execute () {
        List<UserResponseDTO> userResponseDTO = new ArrayList<>();
        userRepository.findAll().forEach((user) -> {
            userResponseDTO.add(formatUserEntityToUserResponseDTO(user));
        });
        return userResponseDTO;
    }

    UserResponseDTO formatUserEntityToUserResponseDTO(UserEntity user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();

    }
}
