package dev.eliezer.lojaonline.modules.product.controller;

import dev.eliezer.lojaonline.modules.product.dtos.CreateProductRequestDTO;
import dev.eliezer.lojaonline.modules.product.dtos.UpdateProductRequestDTO;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.useCases.CreateProductUseCase;
import dev.eliezer.lojaonline.modules.product.useCases.GetProductUseCase;
import dev.eliezer.lojaonline.modules.product.useCases.InactivateProductUseCase;
import dev.eliezer.lojaonline.modules.product.useCases.UpdateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "RESTful API for managing products.")
public class ProductRestController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @Autowired
    private GetProductUseCase getProductUseCase;

    @Autowired
    private InactivateProductUseCase inactivateProductUseCase;

    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Create a new product and return the created product data")
    @ApiResponse(responseCode = "201", description = "Product created successfully", content = {
            @Content(schema = @Schema(implementation = ProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<ProductEntity> execute(@Valid @RequestBody CreateProductRequestDTO product) {
        ProductEntity productSaved = createProductUseCase.execute(product);
        return ResponseEntity.ok().body(productSaved);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
    @ApiResponse(responseCode = "200", description = "Operation sucessfully", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class)))})
    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<ProductEntity>> index(@RequestParam(value = "id", defaultValue = "0") Long productId,
                                                     @RequestParam(value = "name", defaultValue = "") String productName,
                                                     @RequestParam(value = "sku", defaultValue = "") String productSku,
                                                     @RequestParam(value = "type", defaultValue = "") String productType,
                                                     @RequestParam(value = "order", defaultValue = "asc") String productOrder
                                                     ) {
        var result = getProductUseCase.execute(productId, productName, productSku, productType, productOrder);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Update product specified by id and return product data")
    @ApiResponse(responseCode = "201", description = "Product updated successfully", content = {
            @Content(schema = @Schema(implementation = ProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<ProductEntity> execute(@Valid @PathVariable Long id, @RequestBody UpdateProductRequestDTO product) {
        ProductEntity productUpdated = updateProductUseCase.execute(id, product);
        return ResponseEntity.ok().body(productUpdated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Inactivate a product by id", description = "Inactivate product specified by id")
    @ApiResponse(responseCode = "200", description = "Operation sucessfully", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class)))})
    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ProductEntity> inactivate(@PathVariable Long id) {
        var result = inactivateProductUseCase.execute(id);
        return ResponseEntity.ok().body(result);
    }

}
