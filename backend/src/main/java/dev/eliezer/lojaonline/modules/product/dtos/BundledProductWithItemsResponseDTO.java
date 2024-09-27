package dev.eliezer.lojaonline.modules.product.dtos;

import dev.eliezer.lojaonline.modules.product.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.product.entities.BundledProductItemsEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BundledProductWithItemsResponseDTO extends BundledProductEntity {
    private List<BundledProductItemsEntity> items = new ArrayList<>();


}
