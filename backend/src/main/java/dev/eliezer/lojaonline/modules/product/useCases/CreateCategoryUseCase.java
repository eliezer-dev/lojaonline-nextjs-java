package dev.eliezer.lojaonline.modules.product.useCases;

import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.product.dtos.CategoryCreateRequestDTO;
import dev.eliezer.lojaonline.modules.product.dtos.CategoryResponseDTO;
import dev.eliezer.lojaonline.modules.product.entities.CategoryEntity;
import dev.eliezer.lojaonline.modules.product.mappers.CategoryMapper;
import dev.eliezer.lojaonline.modules.product.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCase {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDTO execute(CategoryCreateRequestDTO categoryCreateRequestDTO) {

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setDescription(categoryCreateRequestDTO.getDescription());

        categoryEntity.setVisibleHome(categoryCreateRequestDTO.getVisibleHome());

        if (categoryCreateRequestDTO.getParentCategoryId() != null) {
            CategoryEntity parentCategory = categoryRepository.findById(categoryCreateRequestDTO.getParentCategoryId())
                    .orElseThrow(() -> new NotFoundException(categoryCreateRequestDTO.getParentCategoryId()));

            categoryEntity.setParentCategory(parentCategory);
        }


        return CategoryMapper.toCategoryToResponseDTO(categoryRepository.save(categoryEntity));


    }


}
