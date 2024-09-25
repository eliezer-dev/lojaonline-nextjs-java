package dev.eliezer.lojaonline.modules.product.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "bundled_product_items")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BundledProductItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "id of product")
    private Long id;

    @ManyToOne
    @JoinColumn(name="bundled_product_id")
    private BundledProductEntity bundledProductEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    @NotNull(message = "[stock_quantity] is not provided")
    @Schema(example = "1000", requiredMode = Schema.RequiredMode.REQUIRED, description = "stock quantity of product")
    private Long stock_quantity = 0L;

    @Column(nullable = false, columnDefinition = "numeric(1000,2)")
    @NotNull(message = "[price] is not provided")
    private BigDecimal price = BigDecimal.valueOf(0.00);

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "creation time of product")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "update time of product")
    private LocalDateTime updateAt;

}
