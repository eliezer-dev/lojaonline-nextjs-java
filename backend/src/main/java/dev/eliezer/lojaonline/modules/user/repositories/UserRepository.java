package dev.eliezer.lojaonline.modules.user.repositories;

import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail (String email);
}
