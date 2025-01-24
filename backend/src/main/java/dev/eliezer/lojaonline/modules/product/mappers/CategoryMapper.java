package dev.eliezer.lojaonline.modules.product.mappers;

import dev.eliezer.lojaonline.modules.product.dtos.CategoryResponseDTO;
import dev.eliezer.lojaonline.modules.product.dtos.CategoryWithProductsResponseDTO;
import dev.eliezer.lojaonline.modules.product.dtos.ProductResponseDTO;
import dev.eliezer.lojaonline.modules.product.entities.CategoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryResponseDTO toCategoryToResponseDTO(CategoryEntity categoryEntity) {

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(categoryEntity.getId());
        categoryResponseDTO.setDescription(categoryEntity.getDescription());
        categoryResponseDTO.setVisibleHome(categoryEntity.getVisibleHome());
        categoryResponseDTO.setOrderHomePage(categoryEntity.getOrderHomePage());

        if (categoryEntity.getParentCategory() != null) {
            categoryResponseDTO.setParentCategoryId(categoryEntity.getParentCategory().getId());
        }


        return categoryResponseDTO;
    }

    public static CategoryWithProductsResponseDTO toCategoryWithProductsToResponseDTO(CategoryEntity categoryEntity) {

        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        CategoryWithProductsResponseDTO categoryWithProductsResponseDTO = new CategoryWithProductsResponseDTO();

        categoryWithProductsResponseDTO.setDescription(categoryEntity.getDescription());

        if (categoryEntity.getParentCategory() != null) {
            categoryWithProductsResponseDTO.setParentCategoryId(categoryEntity.getParentCategory().getId());
        }

        if (!categoryEntity.getProducts().isEmpty()) {
            productResponseDTOS = ProductMapper.toProductResponseDTOS(categoryEntity.getProducts());
            categoryWithProductsResponseDTO.setProducts(productResponseDTOS);
        }

        return categoryWithProductsResponseDTO;

    }

    public static List<CategoryWithProductsResponseDTO> toCategoryWithProductsToResponseDTOs(List<CategoryEntity> categoryEntities) {
        List<CategoryWithProductsResponseDTO> categoryWithProductsResponseDTOS = new ArrayList<>();

        categoryEntities.forEach(categoryEntity -> {
            categoryWithProductsResponseDTOS.add(toCategoryWithProductsToResponseDTO(categoryEntity));
        });

        return categoryWithProductsResponseDTOS;
    }
}
