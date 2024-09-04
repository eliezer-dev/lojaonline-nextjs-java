package dev.eliezer.lojaonline.modules.user.controller;

import dev.eliezer.lojaonline.modules.image.entities.ImageEntity;
import dev.eliezer.lojaonline.modules.image.useCases.GetImageUseCase;
import dev.eliezer.lojaonline.modules.user.dtos.CreateUserRequestDTO;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.useCases.InsertUserImageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/users/images")
@Tag(name = "User Image Profile", description = "RESTful API for managing image of user profile.")
public  class UserImageRestController {
    @Autowired
    private InsertUserImageUseCase insertUserImageUseCase;

    @Autowired
    private GetImageUseCase getImageUseCase;

    @PostMapping("/{id}")
    @Operation(summary = "Upload user image",
            description = "Uploads an image file for the specified user"
    )

    @ApiResponse(responseCode = "201", description = "image uploaded successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))})
    @ApiResponse(responseCode = "422", description = "Invalid user data provided", content = {
            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
//    @RequestBody(content =
//    @Content(
//            mediaType = "multipart/form-data",
//            schema = @Schema(
//                    type = "file",
//                    properties = {
//                            @SchemaProperty(
//                                    name = "userImage",
//                                    schema = @Schema(
//                                            description = "User image file to be uploaded",
//                                            type = "string",
//                                            format = "binary"
//                                    )
//                            )
//                    }
//
//            )
//    ))
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<UserResponseDTO> upload(@PathVariable Long id,
                                                  @RequestParam("userImage") MultipartFile file) throws IOException {

        return ResponseEntity.ok().body(insertUserImageUseCase.execute(id, file));
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a user image", description = "Get a image of user")
    @ApiResponse(responseCode = "200", description = "Operation sucessfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(example = "String Base64"))})
    @ApiResponse(responseCode = "422", description = "Invalid user data provided", content = {
            @Content(mediaType = "text/plain", schema = @Schema(example = "Resource id not found."))})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<String> find(@PathVariable Long id) {

        ImageEntity userImageData = getImageUseCase.execute(id);

        MediaType imageType = MediaType.parseMediaType(userImageData.getImageType());

        String userImage = Base64.getEncoder().encodeToString(userImageData.getImageData());

        return ResponseEntity.ok().contentType(imageType).body(userImage);
    }

}
