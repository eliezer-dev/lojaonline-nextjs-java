package dev.eliezer.lojaonline.modules.order.entities;

import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "tb_order_installments")
public class OrderInstallmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "order installments id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, insertable = false, updatable = false)
    private OrderEntity order;

    @NotNull(message = "[paymentMethod] is not provided")
    private Long paymentMethod;

    @NotNull(message = "[installments] is not provided")
    private Long installment;

    @NotNull(message = "[numberOfInstallments] is not provided")
    private Long numberOfInstallments;

}
