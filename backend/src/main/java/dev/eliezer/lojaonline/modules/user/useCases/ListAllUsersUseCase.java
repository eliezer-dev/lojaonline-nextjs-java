package dev.eliezer.lojaonline.modules.user.useCases;

import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllUsersUseCase {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> execute () {
        return userRepository.findAll();
    }
}
