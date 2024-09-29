package dev.eliezer.lojaonline.modules.bundledProduct.repositories;

import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundledProductRepository  extends JpaRepository<BundledProductEntity,Long> {
}
