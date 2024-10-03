package dev.eliezer.lojaonline.modules.bundledProduct.dtos;

import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductItemsEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.repositories.BundledProductItemsRepository;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BundledProductWithItemsResponseDTO extends BundledProductEntity {


    private List<BundledProductItemsEntity> items = new ArrayList<>();


    public BundledProductWithItemsResponseDTO parseBundledProductWithItensResponse (BundledProductEntity bundledProductEntity, List<BundledProductItemsEntity> items ) {

        BundledProductWithItemsResponseDTO bundledProductWithItemsResponse = BundledProductWithItemsResponseDTO.builder()
                .id(bundledProductEntity.getId())
                .name(bundledProductEntity.getName())
                .description(bundledProductEntity.getDescription())
                .sku(bundledProductEntity.getSku())
                .weight(bundledProductEntity.getWeight())
                .price(bundledProductEntity.getPrice())
                .stock_quantity(bundledProductEntity.getStock_quantity())
                .createAt(bundledProductEntity.getCreateAt())
                .updateAt(bundledProductEntity.getUpdateAt())
                .active(bundledProductEntity.getActive())
                .items(items)
                .build();

        return bundledProductWithItemsResponse;
    }

}
