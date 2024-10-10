package dev.eliezer.lojaonline.modules.compositeProduct.repositories;

import dev.eliezer.lojaonline.modules.compositeProduct.entities.BundledProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundledProductRepository  extends JpaRepository<BundledProductEntity,Long> {
}
