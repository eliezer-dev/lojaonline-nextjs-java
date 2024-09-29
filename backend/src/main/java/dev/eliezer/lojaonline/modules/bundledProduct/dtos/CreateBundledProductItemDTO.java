package dev.eliezer.lojaonline.modules.bundledProduct.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBundledProductItemDTO {
    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED, description = "id of product")
    private Long id;

    @Column(nullable = false, columnDefinition = "numeric(1000,2)")
    @NotNull
    private BigDecimal price = BigDecimal.valueOf(0.00);

    @Column(nullable = false, columnDefinition = "bigint default 0")
    @NotNull
    @Schema(example = "1000", requiredMode = Schema.RequiredMode.REQUIRED, description = "stock quantity of product")
    private Long quantity = 0L;

}
