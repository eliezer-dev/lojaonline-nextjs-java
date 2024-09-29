package dev.eliezer.lojaonline.modules.bundledProduct.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.bundledProduct.dtos.BundledProductWithItemsResponseDTO;
import dev.eliezer.lojaonline.modules.bundledProduct.dtos.CreateBundledProductItemDTO;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductItemsEntity;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductItemsRepository;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductRepository;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBundledProductItemUseCase {
    @Autowired
    private BundledProductItemsRepository bundledProductItemsRepository;

    @Autowired
    private BundledProductRepository bundledProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public BundledProductWithItemsResponseDTO execute (CreateBundledProductItemDTO createBundledProductItemDTO, Long idBundledProduct) {
        BundledProductEntity bundledProductFound = bundledProductRepository.findById(idBundledProduct).orElseThrow(() -> new NotFoundException(idBundledProduct));
        ProductEntity productFound = productRepository.findById(createBundledProductItemDTO.getId()).orElseThrow(() -> new NotFoundException(createBundledProductItemDTO.getId()));

        BundledProductItemsEntity bundledProductItems = BundledProductItemsEntity.builder()
                .product(productFound)
                .bundledProductEntity(bundledProductFound)
                .price(createBundledProductItemDTO.getPrice())
                .stock_quantity(createBundledProductItemDTO.getQuantity())
                .build();

        BundledProductItemsEntity bundledProductItemsSaved = bundledProductItemsRepository.save(bundledProductItems);

        BundledProductWithItemsResponseDTO bundledProductWithItemsResponse = BundledProductWithItemsResponseDTO.builder()
                .id(bundledProductFound.getId())
                .name(bundledProductFound.getName())
                .description(bundledProductFound.getDescription())
                .sku(bundledProductFound.getSku())
                .weight(bundledProductFound.getWeight())
                .price(bundledProductFound.getPrice())
                .stock_quantity(bundledProductFound.getStock_quantity())
                .createAt(bundledProductFound.getCreateAt())
                .updateAt(bundledProductFound.getUpdateAt())
                .active(bundledProductFound.getActive())
                .items(bundledProductItemsRepository.findAllByBundledProductEntity_Id(idBundledProduct))
                .build();

        return bundledProductWithItemsResponse;

    }

}
