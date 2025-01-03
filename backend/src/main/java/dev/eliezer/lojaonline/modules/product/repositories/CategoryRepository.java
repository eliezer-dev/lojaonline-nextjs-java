package dev.eliezer.lojaonline.modules.product.repositories;

import dev.eliezer.lojaonline.modules.product.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
