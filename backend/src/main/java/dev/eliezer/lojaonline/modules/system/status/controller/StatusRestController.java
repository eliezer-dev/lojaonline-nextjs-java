package dev.eliezer.lojaonline.modules.system.status.controller;

import dev.eliezer.lojaonline.modules.config.headerMenu.dtos.CategoryItemResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/status")
@Tag(name = "System Status", description = "RESTful API for show a system status")
public class StatusRestController {
    @GetMapping
    @Operation(summary = "Get a system status", description = "Retrieve a system status")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().body("system ok, versão gerada em 15/04/2025 23:47");
    }
}
