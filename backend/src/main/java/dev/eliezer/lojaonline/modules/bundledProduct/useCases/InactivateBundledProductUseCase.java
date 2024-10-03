package dev.eliezer.lojaonline.modules.bundledProduct.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InactivateBundledProductUseCase {
    @Autowired
    BundledProductRepository bundledProductRepository;


    public BundledProductEntity execute (Long id) {
        BundledProductEntity bundledProductToInactivate = bundledProductRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        bundledProductToInactivate.setActive(false);

        return bundledProductRepository.save(bundledProductToInactivate);
    }
}
