package dev.eliezer.lojaonline.modules.product.dtos;

import dev.eliezer.lojaonline.modules.product.entities.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank
    @Schema(example = "Hortifruti", requiredMode = Schema.RequiredMode.REQUIRED, description = "description of category")
    private String description;

    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long parentCategoryId;
}
