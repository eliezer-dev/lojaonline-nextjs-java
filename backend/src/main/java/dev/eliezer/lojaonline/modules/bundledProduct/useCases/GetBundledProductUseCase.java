package dev.eliezer.lojaonline.modules.bundledProduct.useCases;

import dev.eliezer.lojaonline.modules.bundledProduct.dtos.BundledProductWithItemsResponseDTO;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductItemsEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductItemsRepository;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetBundledProductUseCase {
    @Autowired
    private BundledProductRepository bundledProductRepository;

    @Autowired
    private BundledProductItemsRepository bundledProductItemsRepository;

    private BundledProductWithItemsResponseDTO bundledProductWithItemsResponseDTO= new  BundledProductWithItemsResponseDTO ();

    public List<BundledProductWithItemsResponseDTO> execute () {

        List<BundledProductWithItemsResponseDTO> bundledProductWithItemsList = new ArrayList<>();
        bundledProductRepository.findAll()
                .forEach((product) -> {
                    List<BundledProductItemsEntity> items = bundledProductItemsRepository.findAllByBundledProductEntity_Id(product.getId());
                    bundledProductWithItemsList.add(bundledProductWithItemsResponseDTO.parseBundledProductWithItensResponse(product, items));
                });
        return bundledProductWithItemsList;
    }
}
