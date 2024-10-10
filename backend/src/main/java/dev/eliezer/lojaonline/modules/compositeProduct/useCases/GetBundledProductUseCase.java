package dev.eliezer.lojaonline.modules.compositeProduct.useCases;

import dev.eliezer.lojaonline.modules.compositeProduct.dtos.CompositeProductResponseDTO;
import dev.eliezer.lojaonline.modules.compositeProduct.repositories.BundledProductItemsRepository;
import dev.eliezer.lojaonline.modules.compositeProduct.repositories.BundledProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBundledProductUseCase {
    @Autowired
    private BundledProductRepository bundledProductRepository;

    @Autowired
    private BundledProductItemsRepository bundledProductItemsRepository;

    private CompositeProductResponseDTO bundledProductWithItemsResponseDTO= new CompositeProductResponseDTO();

    public void execute () {
    }
}

//        List<CompositeProductResponseDTO> bundledProductWithItemsList = new ArrayList<>();
//        bundledProductRepository.findAll()
//                .forEach((product) -> {
//                    List<BundledProductItemsEntity> items = bundledProductItemsRepository.findAllByBundledProductEntity_Id(product.getId());
//                    bundledProductWithItemsList.add(bundledProductWithItemsResponseDTO.parseBundledProductWithItensResponse(product, items));
//                });
//        return bundledProductWithItemsList;
