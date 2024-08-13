package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.exceptions.EmailNotFoundException;
import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUsersUseCase {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> execute (Long id, String email, String name) {
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();

        if (id == 0 && email.isBlank() && name.isBlank()) {
            userRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).forEach((user) -> {
                userResponseDTOs.add(formatUserEntityToUserResponseDTO(user));
            });
            return userResponseDTOs;
        }

        if (id != 0) {
            UserResponseDTO userResponseDTO = formatUserEntityToUserResponseDTO(userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(id))
            );
            userResponseDTOs.add(userResponseDTO);
            return userResponseDTOs;
        }

        if (!email.isBlank()) {
            UserResponseDTO userResponseDTO = formatUserEntityToUserResponseDTO(userRepository.findByEmail(email)
                    .orElseThrow(() -> new EmailNotFoundException(email))
            );
            userResponseDTOs.add(userResponseDTO);
            return userResponseDTOs;
        }

//        if (!name.isBlank()) {
//            UserResponseDTO userResponseDTO = formatUserEntityToUserResponseDTO(userRepository.findByFullnameIgnoreCaseContaining(name)
//                    .orElseThrow(() -> new EmailNotFoundException(email))
//            );
//            userResponseDTOs.add(userResponseDTO);
//            return userResponseDTOs;
//        }
        return userResponseDTOs;
    }

    UserResponseDTO formatUserEntityToUserResponseDTO(UserEntity user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .active(user.getActive())
                .build();
    }
}
