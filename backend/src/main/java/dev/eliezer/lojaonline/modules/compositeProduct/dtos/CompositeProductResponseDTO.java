package dev.eliezer.lojaonline.modules.compositeProduct.dtos;

import dev.eliezer.lojaonline.modules.compositeProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.compositeProduct.entities.CompositeProductEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CompositeProductResponseDTO extends BundledProductEntity {


    private List<CompositeProductEntity> items = new ArrayList<>();


//    public CompositeProductResponseDTO parseBundledProductWithItensResponse (BundledProductEntity bundledProductEntity, List<BundledProductItemsEntity> items ) {
//
//        CompositeProductResponseDTO bundledProductWithItemsResponse = CompositeProductResponseDTO.builder()
//                .id(bundledProductEntity.getId())
//                .name(bundledProductEntity.getName())
//                .description(bundledProductEntity.getDescription())
//                .sku(bundledProductEntity.getSku())
//                .weight(bundledProductEntity.getWeight())
//                .price(bundledProductEntity.getPrice())
//                .stock_quantity(bundledProductEntity.getStock_quantity())
//                .createAt(bundledProductEntity.getCreateAt())
//                .updateAt(bundledProductEntity.getUpdateAt())
//                .active(bundledProductEntity.getActive())
//                .items(items)
//                .build();
//
//        return bundledProductWithItemsResponse;
//    }

}
