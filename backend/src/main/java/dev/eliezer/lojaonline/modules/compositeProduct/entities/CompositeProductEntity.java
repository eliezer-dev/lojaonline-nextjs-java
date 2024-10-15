package dev.eliezer.lojaonline.modules.compositeProduct.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eliezer.lojaonline.modules.compositeProduct.dtos.ProductItemToCompositeProductDTO;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "composite_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompositeProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "composite_product_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductEntity compositeProduct;

    @Transient
    private String compositeProductName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "composite_item_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductEntity itemProduct;

    @Transient
    private Long compositeItemId;

    @Transient
    private String compositeItemName;

    @Column(nullable = false, columnDefinition = "numeric(1000,2)")
    @NotNull(message = "[price] is not provided")
    private BigDecimal price = BigDecimal.valueOf(0.00);

    @Column(columnDefinition = "bigint default 0")
    @NotNull(message = "[quantity] is not provided")
    private Long quantity = 0L;

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "creation time of product")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "update time of product")
    private LocalDateTime updateAt;

    public Long getCompositeItemId() {
        return itemProduct.getId();
    }

    public void setCompositeItemId(Long compositeItemId) {

    }

    public static CompositeProductEntity parseCompositeProduct(ProductEntity compositeProduct, ProductEntity compositeProductItem, ProductItemToCompositeProductDTO productItemToCompositeProductDTO) {

        return CompositeProductEntity.builder()
                .compositeProduct(compositeProduct)
                .itemProduct(compositeProductItem)
                .price(productItemToCompositeProductDTO.getPrice())
                .quantity(productItemToCompositeProductDTO.getQuantity())
                .build();
    }


    public String getCompositeProductName() {
        if (compositeProduct != null && compositeProduct.getName() != null) {
            return compositeProduct.getName();
        }
        return null;
    }

    public void setCompositeProductDescription(String compositeProductDescription) {

    }

    public String getCompositeItemName() {
        if (itemProduct != null && itemProduct.getName() != null) {
            return itemProduct.getName();
        }
        return null;
    }

    public void setCompositeItemDesciption(String compositeItemDesciption) {
    }
}
