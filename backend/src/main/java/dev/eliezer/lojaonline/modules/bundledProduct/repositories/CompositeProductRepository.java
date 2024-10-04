package dev.eliezer.lojaonline.modules.bundledProduct.repositories;

import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductItemsEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.CompositeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompositeProductRepository extends JpaRepository<CompositeProduct,Long> {
    List<CompositeProduct> findAllByCompositeProduct_Id (Long id);
}
