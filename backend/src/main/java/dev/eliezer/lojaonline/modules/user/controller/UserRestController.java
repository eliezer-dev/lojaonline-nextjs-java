package dev.eliezer.lojaonline.modules.user.controller;

import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.useCases.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "RESTful API for managing users.")
public class UserRestController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user data")
    @ApiResponse(responseCode = "201", description = "User created successfully", content = {
            @Content(schema = @Schema(implementation = UserEntity.class))})
    @ApiResponse(responseCode = "422", description = "User product data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> execute(@Valid @RequestBody UserEntity user) {
        var result = createUserUseCase.execute(user);
        return ResponseEntity.ok().body(result);
    }
}
