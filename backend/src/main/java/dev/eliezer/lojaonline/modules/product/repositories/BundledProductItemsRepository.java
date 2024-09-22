package dev.eliezer.lojaonline.modules.product.repositories;

import dev.eliezer.lojaonline.modules.product.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.product.entities.BundledProductItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundledProductItemsRepository extends JpaRepository<BundledProductItemsEntity,Long> {
}
