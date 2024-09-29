package dev.eliezer.lojaonline.modules.bundledProduct.controller;

import dev.eliezer.lojaonline.exceptions.UnauthorizedAccessException;
import dev.eliezer.lojaonline.modules.bundledProduct.dtos.BundledProductWithItemsResponseDTO;
import dev.eliezer.lojaonline.modules.bundledProduct.dtos.CreateBundledProductItemDTO;
import dev.eliezer.lojaonline.modules.bundledProduct.entities.BundledProductEntity;
import dev.eliezer.lojaonline.modules.bundledProduct.useCases.CreateBundledProductItemUseCase;
import dev.eliezer.lojaonline.modules.bundledProduct.useCases.CreateBundledProductUseCase;
import dev.eliezer.lojaonline.modules.bundledProduct.useCases.UpdateBundledProductUseCase;
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

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/products/bundled")
@Tag(name = "Bundled Products", description = "RESTful API for managing bundled product.")
public class BundledProductRestController {
    @Autowired
    private UpdateBundledProductUseCase updateBundledProductUseCase;

    @Autowired
    private CreateBundledProductUseCase createBundledProductUseCase;


    @Autowired
    private CreateBundledProductItemUseCase createBundledProductItemUseCase;

    @PostMapping
    @Operation(summary = "Create a new bundled product", description = "Create a new bundled product and return the created bundled product data")
    @ApiResponse(responseCode = "201", description = "Bundled product created successfully", content = {
            @Content(schema = @Schema(implementation = BundledProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid bundled product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<BundledProductEntity> createBundledProduct(@Valid @RequestBody BundledProductEntity bundledProduct, HttpServletRequest request) {

        if (Long.valueOf(request.getAttribute("user_role").toString()) != 0) {
            throw  new UnauthorizedAccessException();
        }

        BundledProductEntity bundledProductSaved = createBundledProductUseCase.execute(bundledProduct);

        return ResponseEntity.ok().body(bundledProductSaved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a bundled product", description = "Update bundled product specified by id and return product data")
    @ApiResponse(responseCode = "201", description = "Bundled product updated successfully", content = {
            @Content(schema = @Schema(implementation = BundledProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid bundled product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<BundledProductEntity> updateBundledProduct(@Valid @RequestBody BundledProductEntity bundledProduct, HttpServletRequest request, @PathVariable Long id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        if (Long.valueOf(request.getAttribute("user_role").toString()) != 0) {
            throw  new UnauthorizedAccessException();
        }

        BundledProductEntity bundledProductSaved = updateBundledProductUseCase.execute(bundledProduct, id);

        return ResponseEntity.ok().body(bundledProductSaved);
    }


    @PostMapping("/items/{id}")
    @Operation(summary = "Create a new bundled product", description = "Create a new bundled product and return the created bundled product data")
    @ApiResponse(responseCode = "201", description = "Bundled product created successfully", content = {
            @Content(schema = @Schema(implementation = BundledProductEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid bundled product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<BundledProductWithItemsResponseDTO> createBundledProductItem (@Valid @RequestBody CreateBundledProductItemDTO bundledProductItem, @PathVariable Long id, HttpServletRequest request) {

        if (Long.valueOf(request.getAttribute("user_role").toString()) != 0) {
            throw  new UnauthorizedAccessException();
        }

        BundledProductWithItemsResponseDTO bundledProductItemSaved = createBundledProductItemUseCase.execute(bundledProductItem, id);

        return ResponseEntity.ok().body(bundledProductItemSaved);
    }


}
