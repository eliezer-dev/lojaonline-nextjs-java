package dev.eliezer.lojaonline.modules.product.repositories;

import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("SELECT p FROM tb_product p WHERE SIZE(p.compositeProductEntities) > 0")
    public List<ProductEntity> findAllCompositeProducts ();

    public List<ProductEntity> findAllByNameContaining(String productName);

}
