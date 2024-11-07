package dev.eliezer.lojaonline.modules.order.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eliezer.lojaonline.modules.order.dtos.CreateOrderDTO;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "tb_order")
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "order id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<ProductEntity> products;

    @NotNull(message = "[totalValue] is not provided.")
    @Column(nullable = false)
    private BigDecimal totalValue;

    private String invoiceNumber;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderInstallmentsEntity> orderInstallments;

    @CreationTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "order creation datetime")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Schema(example = "2024-07-21T22:38:10.514664", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "order update datetime")
    private LocalDateTime updateAt;

    @Column(columnDefinition = "boolean default false")
    private Boolean canceled = false;

    private Long canceledBy;

    private LocalDateTime canceledAt;

    private String cancellationReason;


    public OrderEntity (UserEntity user, CreateOrderDTO createOrderDTO) {
        this.user = user;
        this.invoiceNumber = createOrderDTO.getInvoiceNumber();
        this.totalValue = createOrderDTO.getTotalValue();
    }

}
