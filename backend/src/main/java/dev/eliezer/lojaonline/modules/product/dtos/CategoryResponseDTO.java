package dev.eliezer.lojaonline.modules.product.dtos;

import dev.eliezer.lojaonline.modules.product.entities.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryResponseDTO {

    private Long id;

    private String description;

    private Long parentCategoryId;
}
