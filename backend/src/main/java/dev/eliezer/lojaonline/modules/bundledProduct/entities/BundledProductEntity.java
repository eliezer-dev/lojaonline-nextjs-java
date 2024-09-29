package dev.eliezer.lojaonline.modules.bundledProduct.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "bundled_product")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BundledProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "id of product")
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Schema(example = "SmartWatch X2000", requiredMode = Schema.RequiredMode.REQUIRED, description = "short description of product")
    private String name;


    @Schema(example = " SmartWatch X2000 combina estilo e funcionalidade avançada. " +
            "Com tela HD touchscreen de 1.5 polegadas, monitoramento de saúde em tempo real e conectividade Bluetooth 5.0, " +
            "este relógio inteligente é perfeito para quem busca praticidade no dia a dia. " +
            "Além disso, é resistente à água e possui bateria de longa duração, proporcionando " +
            "uma experiência completa para acompanhar seu estilo de vida ativo.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "complete description of product")
    private String description;

    @Schema(example = "laraj-1234", requiredMode = Schema.RequiredMode.REQUIRED,
            description = "sku of product, sku is an alphanumeric code, used to identify the product for stock control, " +
                    "logistics and inventories. ")
    private String sku;

    @Column(nullable = false, columnDefinition = "numeric(1000,2)")
    @NotNull
    private BigDecimal price = BigDecimal.valueOf(0.00);

    @Column(nullable = false, columnDefinition = "bigint default 0")
    @NotNull
    @Schema(example = "1000", requiredMode = Schema.RequiredMode.REQUIRED, description = "stock quantity of product")
    private Long stock_quantity = 0L;

    @Column(nullable = false, columnDefinition = "numeric(1000,3)")
    @Schema(example = "0.000", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "weight in KG of product")
    private BigDecimal weight = BigDecimal.valueOf(0.00);

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "creation time of product")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "update time of product")
    private LocalDateTime updateAt;

    @Column(columnDefinition = "boolean default true")
    @Schema(example = "true", description = "product active")
    private Boolean active = true;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany()
    @JoinTable(
            name = "bundled_product_items",
            joinColumns = @JoinColumn(name = "bundled_product_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<ProductEntity> products = new ArrayList<>();

}