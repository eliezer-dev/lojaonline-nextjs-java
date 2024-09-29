package dev.eliezer.lojaonline.modules.bundledProduct.useCases;

import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBundledProductUseCase {
    @Autowired
    private BundledProductRepository bundledProductRepository;

    public BundledProductEntity execute (BundledProductEntity bundledProduct) {
        BundledProductEntity bundledProductEntitySaved = bundledProductRepository.save(bundledProduct);
        return bundledProductEntitySaved;
    }


}
