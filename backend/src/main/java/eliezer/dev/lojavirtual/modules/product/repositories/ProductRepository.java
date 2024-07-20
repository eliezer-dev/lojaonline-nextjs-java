package eliezer.dev.lojavirtual.modules.product.repositories;

import eliezer.dev.lojavirtual.modules.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
