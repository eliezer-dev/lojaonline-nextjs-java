package dev.eliezer.lojaonline.modules.order.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    @NotNull(message = "[userId] is not provided.")
    private Long userId;

    @NotNull(message = "[orderItems] is not provided")
    private List<CreateOrderItemDTO> orderItems = new ArrayList<>();

    @NotNull(message = "[totalValue] is not provided.")
    private BigDecimal totalValue;

    private String invoiceNumber;

    @NotNull(message = "[orderInstallments] is not provided")
    private List<CreateOrderInstallmentsDTO> orderInstallments = new ArrayList<>();


}
