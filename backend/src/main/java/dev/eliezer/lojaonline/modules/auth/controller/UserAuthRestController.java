package dev.eliezer.lojaonline.modules.auth.controller;

import dev.eliezer.lojaonline.modules.auth.dto.AuthUserRequestDTO;
import dev.eliezer.lojaonline.modules.auth.dto.AuthUserResponseDTO;
import dev.eliezer.lojaonline.modules.auth.useCases.AuthUserUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
@Tag(name = "Users Auth", description = "RESTful API for authenticating users")
public class UserAuthRestController {

    @Autowired
    private AuthUserUseCase authUserUseCase;

    @PostMapping
//    @Operation(summary = "Create a new user", description = "Create a new user and return the created user data")
//    @ApiResponse(responseCode = "201", description = "User created successfully", content = {
//            @Content(schema = @Schema(implementation = UserEntity.class))})
//    @ApiResponse(responseCode = "422", description = "User product data provided", content = {
//            @Content(schema = @Schema(implementation = Object.class))})
    @SecurityRequirement(name = "jwt_auth")
    public AuthUserResponseDTO auth (@RequestBody AuthUserRequestDTO userAuth) {
        return authUserUseCase.execute(userAuth);
    }
}
