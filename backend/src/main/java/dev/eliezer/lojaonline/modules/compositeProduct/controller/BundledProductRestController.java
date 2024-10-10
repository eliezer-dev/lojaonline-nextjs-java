package dev.eliezer.lojaonline.modules.compositeProduct.controller;

import dev.eliezer.lojaonline.exceptions.UnauthorizedAccessException;
import dev.eliezer.lojaonline.modules.compositeProduct.dtos.ProductItemToCompositeProductDTO;
import dev.eliezer.lojaonline.modules.compositeProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.compositeProduct.useCases.*;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/composite")
@Tag(name = "Bundled Products", description = "RESTful API for managing bundled product.")
public class BundledProductRestController {
    @Autowired
    private UpdateBundledProductUseCase updateBundledProductUseCase;

    @Autowired
    private InsertItemsCompositeProductUseCase insertItemsCompositeProductUseCase;
    
    @Autowired
    private InactivateBundledProductUseCase inactivateBundledProductUseCase;
    
    @Autowired
    private GetBundledProductUseCase getBundledProductUseCase;

//    @GetMapping
//    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
//    @ApiResponse(responseCode = "200", description = "Operation sucessfully", content = {
//            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class)))})
//    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
//            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
//    @SecurityRequirement(name = "jwt_auth")
//    public ResponseEntity<List<CompositeProductResponseDTO>> index() {
//        var result = getBundledProductUseCase.execute();
//        return ResponseEntity.ok().body(result);
//    }


//    @PostMapping
//    @Operation(summary = "Create a new bundled product", description = "Create a new bundled product and return the created bundled product data")
//    @ApiResponse(responseCode = "201", description = "Bundled product created successfully", content = {
//            @Content(schema = @Schema(implementation = BundledProductEntity.class))})
//    @ApiResponse(responseCode = "422", description = "Invalid bundled product data provided", content = {
//            @Content(schema = @Schema(implementation = Object.class))})
//    public ResponseEntity<BundledProductEntity> createBundledProduct(@Valid @RequestBody BundledProductEntity bundledProduct, HttpServletRequest request) {
//
//        if (Long.valueOf(request.getAttribute("user_role").toString()) != 0) {
//            throw  new UnauthorizedAccessException();
//        }
//
//        BundledProductEntity bundledProductSaved = createBundledProductUseCase.execute(bundledProduct);
//
//        return ResponseEntity.ok().body(bundledProductSaved);
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Update a bundled product", description = "Update bundled product specified by id and return product data")
//    @ApiResponse(responseCode = "201", description = "Bundled product updated successfully", content = {
//            @Content(schema = @Schema(implementation = BundledProductEntity.class))})
//    @ApiResponse(responseCode = "422", description = "Invalid bundled product data provided", content = {
//            @Content(schema = @Schema(implementation = Object.class))})
//    public ResponseEntity<BundledProductEntity> updateBundledProduct(@Valid @RequestBody BundledProductEntity bundledProduct, HttpServletRequest request, @PathVariable Long id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//
//        if (Long.valueOf(request.getAttribute("user_role").toString()) != 0) {
//            throw  new UnauthorizedAccessException();
//        }
//
//        BundledProductEntity bundledProductSaved = updateBundledProductUseCase.execute(bundledProduct, id);
//
//        return ResponseEntity.ok().body(bundledProductSaved);
//    }


    @PostMapping("/items/{id}")
    @Operation(summary = "Create a new composite product", description = "Create a new composite product and return the created bundled product data")
    @ApiResponse(responseCode = "201", description = "Bundled product created successfully", content = {
            @Content(schema = @Schema(implementation = BundledProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid bundled product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<ProductEntity> createBundledProductItem (@Valid @RequestBody ProductItemToCompositeProductDTO compositeProductItem, @PathVariable Long id, HttpServletRequest request) {

        if (Long.valueOf(request.getAttribute("user_role").toString()) != 0) {
            throw  new UnauthorizedAccessException();
        }

        ProductEntity compositeProductSaved = insertItemsCompositeProductUseCase.execute(compositeProductItem, id);

        return ResponseEntity.ok().body(compositeProductSaved);
    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Inactivate a bundled product by id", description = "Inactivate product specified by id")
//    @ApiResponse(responseCode = "200", description = "Operation sucessfully", content = {
//            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class)))})
//    @ApiResponse(responseCode = "422", description = "Invalid product data provided", content = {
//            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
//    @SecurityRequirement(name = "jwt_auth")
//    public ResponseEntity<BundledProductEntity> inactivate(@PathVariable Long id) {
//        var result = inactivateBundledProductUseCase.execute(id);
//        return ResponseEntity.ok().body(result);
//    }


}
