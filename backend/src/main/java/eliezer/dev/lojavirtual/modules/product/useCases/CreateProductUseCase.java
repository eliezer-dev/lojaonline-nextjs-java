package eliezer.dev.lojavirtual.modules.product.useCases;

import eliezer.dev.lojavirtual.exceptions.ProductFoundException;
import eliezer.dev.lojavirtual.modules.product.entities.ProductEntity;
import eliezer.dev.lojavirtual.modules.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {
    @Autowired
    private ProductRepository productRepository;

    public ProductEntity execute (ProductEntity productEntity) {
        return productRepository.save(productEntity);

    }
}
