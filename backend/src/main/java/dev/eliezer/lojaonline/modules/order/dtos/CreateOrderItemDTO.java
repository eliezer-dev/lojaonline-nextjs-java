package dev.eliezer.lojaonline.modules.order.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class CreateOrderItemDTO {

    @NotNull(message = "[productId] is not provided")
    private Long productId;

    @NotNull(message = "[quantity] is not provided")
    private Long quantity;

    @NotNull(message = "[price] is not provided")
    private BigDecimal price;

}
