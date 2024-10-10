package dev.eliezer.lojaonline.modules.compositeProduct.repositories;

import dev.eliezer.lojaonline.modules.compositeProduct.entities.BundledProductItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BundledProductItemsRepository extends JpaRepository<BundledProductItemsEntity,Long> {
    List<BundledProductItemsEntity> findAllByBundledProductEntity_Id (Long id);
}
