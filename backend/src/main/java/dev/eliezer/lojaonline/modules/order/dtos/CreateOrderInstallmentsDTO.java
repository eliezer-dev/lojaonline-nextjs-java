package dev.eliezer.lojaonline.modules.order.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateOrderInstallmentsDTO {

    @NotNull(message = "[paymentMethod] is not provided")
    private Long paymentMethod;

    @NotNull(message = "[installments] is not provided")
    private Long installment;

    @NotNull(message = "[numberOfInstallments] is not provided")
    private Long numberOfInstallments;
}
