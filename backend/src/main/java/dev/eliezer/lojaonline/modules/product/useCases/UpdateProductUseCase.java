package dev.eliezer.lojaonline.modules.product.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.product.dtos.UpdateProductRequestDTO;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity execute(Long id, UpdateProductRequestDTO request) {

        ProductEntity productToUpdate = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        productToUpdate.setName(request.getName() != null ? request.getName() : productToUpdate.getName());
        productToUpdate.setDescription(request.getDescription() != null ? request.getDescription() : productToUpdate.getDescription());
        productToUpdate.setSku(request.getSku() != null ? request.getSku() : productToUpdate.getSku());
        productToUpdate.setStock_quantity(request.getStock_quantity() != null ? request.getStock_quantity() : productToUpdate.getStock_quantity());
        productToUpdate.setPrice(request.getPrice() != null ? request.getPrice() : productToUpdate.getPrice());
        productToUpdate.setWeight(request.getWeight() != null ? request.getWeight() : productToUpdate.getWeight());

        return productRepository.save(productToUpdate);


    }
}
