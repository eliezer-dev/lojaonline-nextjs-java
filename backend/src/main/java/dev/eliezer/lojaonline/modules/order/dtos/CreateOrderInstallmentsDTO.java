package dev.eliezer.lojaonline.modules.order.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderInstallmentsDTO {

    @NotNull(message = "[paymentMethod] is not provided")
    private Long paymentMethod;

    @NotNull(message = "[installments] is not provided")
    private Long installment;

/*    @JsonIgnore
    private LocalDate dueDate = LocalDate.now().plusMonths(installment);*/

    @NotNull(message = "[numberOfInstallments] is not provided")
    private Long numberOfInstallments;
}
