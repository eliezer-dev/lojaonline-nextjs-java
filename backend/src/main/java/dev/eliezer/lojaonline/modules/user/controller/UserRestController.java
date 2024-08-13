package dev.eliezer.lojaonline.modules.user.controller;

import dev.eliezer.lojaonline.modules.user.dtos.UpdateUserRequestDTO;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.dtos.CreateUserRequestDTO;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.useCases.CreateUserUseCase;
import dev.eliezer.lojaonline.modules.user.useCases.InactivateUserUseCase;
import dev.eliezer.lojaonline.modules.user.useCases.GetUsersUseCase;
import dev.eliezer.lojaonline.modules.user.useCases.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@RequestMapping("/users")
@Tag(name = "Users", description = "RESTful API for managing users.")
public class UserRestController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private GetUsersUseCase getUsersUseCase;

    @Autowired
    private UpdateUserUseCase updateUserUseCase;

    @Autowired
    private InactivateUserUseCase inactivateUserUseCase;

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @ApiResponse(responseCode = "200", description = "Operation sucessfully", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserEntity.class)))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<UserResponseDTO>> index(@RequestParam(value = "id", defaultValue = "0") Long id,
                                                       @RequestParam(value = "email", defaultValue = "") String email,
                                                       @RequestParam(value = "name", defaultValue = "") String name) {
        var result = getUsersUseCase.execute(id, email, name);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user and return the created user data")
    @ApiResponse(responseCode = "201", description = "User created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))})
    @ApiResponse(responseCode = "422", description = "Invalid user data provided", content =
    @Content(
            mediaType = "text/plain",
            array = @ArraySchema(schema = @Schema(implementation = Object.class)),
            examples = {
                    @ExampleObject(
                            name = "RequiredFieldsMissing",
                            value = "[\"email not provided\", \"fullname not provided\", \"password not provided\"]"
                    ),
                    @ExampleObject(
                            name = "OtherErrorMessages",
                            value = "[\"email is alright in use.\"]"
                    )
            }
    ))
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody CreateUserRequestDTO user) {
        var result = createUserUseCase.execute(user);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update a user and return the user data updated")
    @ApiResponse(responseCode = "201", description = "User updated successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUserRequestDTO.class))})
    @ApiResponse(responseCode = "404", description = "user id not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
    @ApiResponse(responseCode = "422", description = "Invalid user data provided", content = {
            @Content(mediaType = "text/plain", schema = @Schema(example = "email is alright in use."))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDTO user) {
        var result = updateUserUseCase.execute(user, id);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Inactivate a user", description = "Inactivate a user")
    @ApiResponse(responseCode = "200", description = "User inactivated successfully", content = {
            @Content(schema = @Schema(implementation = UserEntity.class))})
    @ApiResponse(responseCode = "404", description = "user id not found", content = {
            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<String> inactivate(@PathVariable Long id ) {
        inactivateUserUseCase.execute(id);
        return ResponseEntity.ok().body("User with id " + id +  " inactivated successfully.");
    }


}
