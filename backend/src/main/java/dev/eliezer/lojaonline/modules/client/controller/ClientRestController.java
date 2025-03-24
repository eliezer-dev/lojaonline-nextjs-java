package dev.eliezer.lojaonline.modules.client.controller;

import dev.eliezer.lojaonline.modules.client.dtos.CreateClientDTO;
import dev.eliezer.lojaonline.modules.client.dtos.CreateResponseClientDTO;
import dev.eliezer.lojaonline.modules.client.useCases.CreateClientUseCase;
import dev.eliezer.lojaonline.modules.user.dtos.UserResponseDTO;
import dev.eliezer.lojaonline.modules.user.useCases.CreateUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "RESTful API for managing clients.")
public class ClientRestController {
    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private CreateClientUseCase createClientUseCase;

    @PostMapping("/create")
    @Operation(summary = "Create a new user of the client type", description = "Create a new user of the client type and return the created user data")
    @ApiResponse(responseCode = "201", description = "User created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class))})
    @ApiResponse(responseCode = "422", description = "Invalid user data provided", content =
    @Content(
            mediaType = "text/plain",
            array = @ArraySchema(schema = @Schema(implementation = CreateClientDTO.class)),

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
    public ResponseEntity<CreateResponseClientDTO> create(@Valid @RequestBody CreateClientDTO requestData, HttpServletRequest request) {

        var result = createClientUseCase.execute(requestData);
        return ResponseEntity.ok().body(result);
    }


}
