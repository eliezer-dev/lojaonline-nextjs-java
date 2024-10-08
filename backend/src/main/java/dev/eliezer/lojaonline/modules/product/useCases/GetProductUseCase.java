package dev.eliezer.lojaonline.modules.product.useCases;

import dev.eliezer.lojaonline.modules.product.dtos.CreateProductRequestDTO;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> execute () {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));

    }
}
