package dev.eliezer.lojaonline.modules.user.controller;

import dev.eliezer.lojaonline.modules.user.dtos.CreateUserRequestDTO;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.useCases.InsertUserImageUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users/images")
@Tag(name = "Images", description = "RESTful API for managing user images.")
public  class UserImageRestController {
    @Autowired
    private InsertUserImageUseCase insertUserImageUseCase;

    @PostMapping("/{id}")
//    @Operation(summary = "Create a new user", description = "Create a new user and return the created user data")
//    @ApiResponse(responseCode = "201", description = "User created successfully", content = {
//            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))})
//    @ApiResponse(responseCode = "422", description = "Invalid user data provided", content =
//    @Content(
//            mediaType = "text/plain",
//            array = @ArraySchema(schema = @Schema(implementation = Object.class)),
//
//            examples = {
//                    @ExampleObject(
//                            name = "RequiredFieldsMissing",
//                            value = "[\"email not provided\", \"fullname not provided\", \"password not provided\"]"
//                    ),
//                    @ExampleObject(
//                            name = "OtherErrorMessages",
//                            value = "[\"email is alright in use.\"]"
//                    )
//            }
//    ))
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@PathVariable Long id, @RequestParam("avatar") MultipartFile file) throws IOException {

        String image = insertUserImageUseCase.execute(id, file);
        return ResponseEntity.ok().body(image);
    }
}
