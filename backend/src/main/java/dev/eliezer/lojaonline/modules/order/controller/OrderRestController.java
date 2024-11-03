package dev.eliezer.lojaonline.modules.order.controller;

import dev.eliezer.lojaonline.exceptions.BusinessException;
import dev.eliezer.lojaonline.exceptions.UnauthorizedAccessException;
import dev.eliezer.lojaonline.modules.client.dtos.CreateUserClientTypeDTO;
import dev.eliezer.lojaonline.modules.client.useCases.CreateUserClientTypeUseCase;
import dev.eliezer.lojaonline.modules.order.dtos.CreateOrderDTO;
import dev.eliezer.lojaonline.modules.order.dtos.OrderResponseDTO;
import dev.eliezer.lojaonline.modules.order.entities.OrderEntity;
import dev.eliezer.lojaonline.modules.order.repositories.OrderRepository;
import dev.eliezer.lojaonline.modules.order.useCases.CreateOrderUseCase;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
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

import static dev.eliezer.lojaonline.utils.RequestUtils.isClientUser;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "RESTful API for managing orders.")
public class OrderRestController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @PostMapping
    @Operation(summary = "Create a new order", description = "Create a new order and return the created order data")
    @ApiResponse(responseCode = "201", description = "Order created successfully", content = {
            @Content(schema = @Schema(implementation = OrderEntity.class))})
    @ApiResponse(responseCode = "422", description = "Invalid order data provided", content = {
            @Content(schema = @Schema(implementation = Object.class))})
    public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody CreateOrderDTO orderDTO, HttpServletRequest request) {

        if (!isClientUser(request)) {
            throw new UnauthorizedAccessException();
        }

       OrderResponseDTO orderSaved =  createOrderUseCase.execute(orderDTO);
        return ResponseEntity.ok().body(orderSaved);
    }




}
