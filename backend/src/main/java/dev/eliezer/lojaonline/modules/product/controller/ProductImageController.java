package dev.eliezer.lojaonline.modules.product.controller;

import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.useCases.UploadProductImageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products/images/{id}")
@Tag(name = "Products Images", description = "RESTful API for managing products images.")
public class ProductImageController {
    @Autowired
    private UploadProductImageUseCase uploadProductImageUseCase;


    @PostMapping
    @Operation(summary = "Create a new product", description = "Create a new product and return the created product data")
    @ApiResponse(responseCode = "201", description = "Product created successfully", content = {
            @Content(schema = @Schema(implementation = ProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ProductEntity> execute(@RequestParam("image") MultipartFile file, @PathVariable Long id) throws IOException {
        ProductEntity productEntity = uploadProductImageUseCase.execute(file,id);


        return ResponseEntity.ok(productEntity);
    }

}
