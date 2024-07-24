package dev.eliezer.lojaonline.modules.user.repositories;

import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
