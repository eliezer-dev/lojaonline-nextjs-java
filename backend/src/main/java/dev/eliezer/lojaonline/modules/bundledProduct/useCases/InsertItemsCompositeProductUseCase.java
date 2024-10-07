package dev.eliezer.lojaonline.modules.bundledProduct.useCases;

import dev.eliezer.lojaonline.exceptions.BusinessException;
import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.bundledProduct.dtos.ProductItemToCompositeProductDTO;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.CompositeProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.CompositeProductRepository;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertItemsCompositeProductUseCase {
    @Autowired
    private CompositeProductRepository compositeProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity execute (ProductItemToCompositeProductDTO compositeProductItemRequest, Long idCompositeProduct) {

        ProductEntity compositeproductFound = productRepository.findById(idCompositeProduct)
                .orElseThrow(() -> new NotFoundException(idCompositeProduct));
        ProductEntity productItemFound = productRepository.findById(compositeProductItemRequest.getId())
                .orElseThrow(() -> new NotFoundException(compositeProductItemRequest.getId()));

        compositeProductRepository.findByCompositeProduct_IdAndItemProduct_Id(idCompositeProduct, compositeProductItemRequest.getId())
                .ifPresent(
                       item -> {
                            throw new BusinessException("Composite item with id " + compositeProductItemRequest.getId() + " is alright exists.");
                       });


        CompositeProductEntity compositeProductItemToSave = CompositeProductEntity.builder()
                .compositeProduct(compositeproductFound)
                .itemProduct(productItemFound)
                .price(compositeProductItemRequest.getPrice())
                .quantity(compositeProductItemRequest.getQuantity())
                .build();

        CompositeProductEntity compositeProductSaved = compositeProductRepository.save(compositeProductItemToSave);

        compositeproductFound.getCompositeItems().add(compositeProductSaved);

        return compositeproductFound;

    }

}
