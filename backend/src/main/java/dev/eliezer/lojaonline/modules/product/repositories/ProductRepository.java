package dev.eliezer.lojaonline.modules.product.repositories;

import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
