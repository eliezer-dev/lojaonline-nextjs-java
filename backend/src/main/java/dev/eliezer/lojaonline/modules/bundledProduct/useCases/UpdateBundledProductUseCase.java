package dev.eliezer.lojaonline.modules.bundledProduct.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateBundledProductUseCase {
    @Autowired
    private BundledProductRepository bundledProductRepository;

    public BundledProductEntity execute (BundledProductEntity bundledProduct, Long id) {
        BundledProductEntity bundledProductToUpdate = bundledProductRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        bundledProductToUpdate.setActive(bundledProduct.getActive() != null ? bundledProduct.getActive() : bundledProductToUpdate.getActive());
        bundledProductToUpdate.setName(bundledProduct.getName() != null ? bundledProduct.getName() : bundledProductToUpdate.getName());
        bundledProductToUpdate.setPrice(bundledProduct.getPrice() != null ? bundledProduct.getPrice() : bundledProductToUpdate.getPrice());
        bundledProductToUpdate.setDescription(bundledProduct.getDescription() != null ? bundledProduct.getDescription() : bundledProductToUpdate.getDescription());
        bundledProductToUpdate.setStock_quantity(bundledProduct.getStock_quantity() != null ? bundledProduct.getStock_quantity() : bundledProductToUpdate.getStock_quantity());
        bundledProductToUpdate.setWeight(bundledProduct.getWeight() != null ? bundledProduct.getWeight() : bundledProductToUpdate.getWeight());

        return bundledProductRepository.save(bundledProductToUpdate);
    }


}
