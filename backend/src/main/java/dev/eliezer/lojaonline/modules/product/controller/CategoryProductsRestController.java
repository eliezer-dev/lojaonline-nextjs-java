package dev.eliezer.lojaonline.modules.product.controller;

import dev.eliezer.lojaonline.modules.product.dtos.CategoryRequestDTO;
import dev.eliezer.lojaonline.modules.product.dtos.CategoryResponseDTO;
import dev.eliezer.lojaonline.modules.product.useCases.CreateCategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
@Tag(name = "Products Categories", description = "RESTful API for managing products categories.")
public class CategoryProductsRestController {
    @Autowired
    private CreateCategoryUseCase createCategoryUseCase;


    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category and return the created category data")
    @ApiResponse(responseCode = "201", description = "Category created successfully", content = {
            @Content(schema = @Schema(implementation = CategoryRequestDTO.class))})
    @ApiResponse(responseCode = "422", description = "Invalid category data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<CategoryResponseDTO> execute(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO categorySaved = createCategoryUseCase.execute(categoryRequestDTO);

        return ResponseEntity.ok().body(categorySaved);
    }

}
