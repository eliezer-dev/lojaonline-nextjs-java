package dev.eliezer.lojaonline.modules.product.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.product.dtos.CategoryRequestDTO;
import dev.eliezer.lojaonline.modules.product.dtos.CategoryResponseDTO;
import dev.eliezer.lojaonline.modules.product.entities.CategoryEntity;
import dev.eliezer.lojaonline.modules.product.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCase {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDTO execute(CategoryRequestDTO categoryRequestDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setDescription(categoryRequestDTO.getDescription());

        if (categoryRequestDTO.getParentCategoryId() != null) {
            CategoryEntity parentCategory = categoryRepository.findById(categoryRequestDTO.getParentCategoryId())
                    .orElseThrow(() -> new NotFoundException(categoryRequestDTO.getParentCategoryId()));

            categoryEntity.setParentCategory(parentCategory);
        }


        return convertCategoryToResponseDTO(categoryRepository.save(categoryEntity));


    }

    private CategoryResponseDTO convertCategoryToResponseDTO(CategoryEntity categoryEntity) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(categoryEntity.getId());
        categoryResponseDTO.setDescription(categoryEntity.getDescription());

        if (categoryEntity.getParentCategory() != null) {
            categoryResponseDTO.setParentCategoryId(categoryEntity.getParentCategory().getId());
        }


        return categoryResponseDTO;
    }
}
