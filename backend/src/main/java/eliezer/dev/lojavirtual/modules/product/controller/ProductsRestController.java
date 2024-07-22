package eliezer.dev.lojavirtual.modules.product.controller;

import eliezer.dev.lojavirtual.modules.product.entities.ProductEntity;
import eliezer.dev.lojavirtual.modules.product.useCases.CreateProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "RESTful API for managing products.")
public class ProductsRestController {
    @Autowired
    private CreateProductUseCase createProductUseCase;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Create a new product and return the created product data")
    @ApiResponse(responseCode = "201", description = "Product created successfully", content = {
            @Content(schema = @Schema(implementation = ProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<Object> execute(@RequestBody ProductEntity product) {
        try {
            var result = createProductUseCase.execute(product);
            return ResponseEntity.ok().body(result);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
