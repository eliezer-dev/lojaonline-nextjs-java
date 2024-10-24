package dev.eliezer.lojaonline.modules.product.useCases;

import dev.eliezer.lojaonline.exceptions.BusinessException;
import dev.eliezer.lojaonline.modules.product.dtos.CreateProductRequestDTO;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<ProductEntity> execute (Long productId, String productName, String productSku, String productType, String productOrder) {
        if (!productId.equals(0L)) {
            return productRepository.findById(productId).stream().toList();
        }

        if (!productName.isBlank()) {
            var productsList = productRepository.findAllByNameContainingIgnoreCase(productName);
            return productsList;
        }

        if (!productSku.isBlank()) {
            var productsList = productRepository.findAllBySku(productSku);
            return productsList;
        }

        if (!productType.isBlank()) {
            List<ProductEntity> productsList = new ArrayList<>();

            switch (productType) {
                case "composite":
                    productsList = productRepository.findAllCompositeProducts();
                    return productsList;

                case "simple":
                    var productList = productRepository.findAllSimpleProducts();
                    return productList;

                default:
                    throw new BusinessException("product type invalid");
            }
        }

        return productRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));

    }
}
