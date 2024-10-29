package dev.eliezer.lojaonline.modules.product.useCases;

import dev.eliezer.lojaonline.exceptions.BusinessException;
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

    private static final List<String> LIST_FIELDS_ORDER = new ArrayList<>(List.of("id", "name", "sku", "price" ));

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<ProductEntity> execute (Long productId, String productName, String productSku, String productType,
                                        String fieldOrder, String sortDirection) {

        if (!LIST_FIELDS_ORDER.contains(fieldOrder)) fieldOrder = "id";

        Sort sortByAndDirection = Sort.by(!sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.ASC
                                          : Sort.Direction.DESC,fieldOrder);


        if (!productId.equals(0L)) {
            return productRepository.findById(productId).stream().toList();
        }

        if (!productName.isBlank()) {
            var productsList = productRepository.findAllByNameContainingIgnoreCase(productName, sortByAndDirection);
            return productsList;
        }

        if (!productSku.isBlank()) {
            var productsList = productRepository.findAllBySku(productSku,sortByAndDirection);
            return productsList;
        }
        if (!productType.isBlank()) {
            List<ProductEntity> productsList = new ArrayList<>();
            switch (productType) {
                case "composite":
                    productsList = productRepository.findAllCompositeProducts(sortByAndDirection);
                    return productsList;

                case "simple":
                    var productList = productRepository.findAllSimpleProducts(sortByAndDirection);
                    return productList;
                default:
                    throw new BusinessException("product type invalid");
            }
        }

        return productRepository.findAll(sortByAndDirection);

    }
}
