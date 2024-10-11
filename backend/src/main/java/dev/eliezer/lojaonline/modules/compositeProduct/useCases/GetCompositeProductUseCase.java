package dev.eliezer.lojaonline.modules.compositeProduct.useCases;

import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCompositeProductUseCase {
    @Autowired
    ProductRepository productRepository;

    public List<ProductEntity> execute () {
        return productRepository.findAllCompositeProducts();
    }
}
