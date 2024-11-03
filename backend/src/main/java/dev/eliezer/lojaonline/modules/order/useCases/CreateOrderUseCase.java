package dev.eliezer.lojaonline.modules.order.useCases;

import dev.eliezer.lojaonline.exceptions.BusinessException;
import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.order.dtos.CreateOrderDTO;
import dev.eliezer.lojaonline.modules.order.dtos.OrderInstallmentsResponseDTO;
import dev.eliezer.lojaonline.modules.order.dtos.OrderItemResponseDTO;
import dev.eliezer.lojaonline.modules.order.dtos.OrderResponseDTO;
import dev.eliezer.lojaonline.modules.order.entities.OrderEntity;
import dev.eliezer.lojaonline.modules.order.entities.OrderInstallmentsEntity;
import dev.eliezer.lojaonline.modules.order.entities.OrderItemEntity;
import dev.eliezer.lojaonline.modules.order.repositories.OrderInstallmentsRepository;
import dev.eliezer.lojaonline.modules.order.repositories.OrderItemRepository;
import dev.eliezer.lojaonline.modules.order.repositories.OrderRepository;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateOrderUseCase {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderInstallmentsRepository orderInstallmentsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity user;

    private CreateOrderDTO createOrderDTO;

    private OrderEntity orderEntity;

    private List<OrderItemResponseDTO> orderItemEntityList = new ArrayList<>();

    private List<OrderInstallmentsResponseDTO> orderInstallmentsEntityList = new ArrayList<>();


    public OrderResponseDTO execute (CreateOrderDTO dataReq) {

        this.user = userRepository.findById(dataReq.getUserId()).orElseThrow(() -> new NotFoundException(dataReq.getUserId()));

        this.createOrderDTO = dataReq;

        saveOrder ();

        saveOrderItem();

        saveOrderInstallments ();

        OrderResponseDTO orderResponseDTO = OrderResponseDTO.builder()
                .id(orderEntity.getId())
                .userId(orderEntity.getUser().getId())
                .orderItems(orderItemEntityList)
                .orderInstallments(orderInstallmentsEntityList)
                .invoiceNumber(orderEntity.getInvoiceNumber())
                .createAt(orderEntity.getCreateAt())
                .updateAt(orderEntity.getUpdateAt())
                .totalValue(orderEntity.getTotalValue())
                .build();


        return orderResponseDTO;

    }

    public void saveOrder () {
           orderEntity = orderRepository.save(OrderEntity.builder()
                .user(user)
                .invoiceNumber(createOrderDTO.getInvoiceNumber())
                .totalValue(createOrderDTO.getTotalValue())
                .build());
    }

    public void saveOrderItem() {

        createOrderDTO.getOrderItems().forEach(item -> {
            ProductEntity productEntity = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new NotFoundException(item.getProductId()));

            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order(orderEntity)
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .product(productEntity)
                    .build();

            OrderItemEntity orderItemSaved = orderItemRepository.save(orderItem);
            OrderItemResponseDTO orderItemResponseDTO = OrderItemResponseDTO.builder()
                    .id(orderItemSaved.getId())
                    .productId(orderItemSaved.getProduct().getId())
                    .price(orderItemSaved.getPrice())
                    .quantity(orderItemSaved.getQuantity())
                    .createAt(orderItemSaved.getCreateAt())
                    .updateAt(orderItemSaved.getUpdateAt())
                    .build();

            orderItemEntityList.add(orderItemResponseDTO);
        });
    }

    public void saveOrderInstallments () {

        createOrderDTO.getOrderInstallments().forEach(orderInstallmentsDTO -> {
            OrderInstallmentsEntity orderInstallmentsEntity = OrderInstallmentsEntity.builder()
                    .order(orderEntity)
                    .installment(orderInstallmentsDTO.getInstallment())
                    .numberOfInstallments(orderInstallmentsDTO.getNumberOfInstallments())
                    .paymentMethod(orderInstallmentsDTO.getPaymentMethod())
                    .build();

            OrderInstallmentsEntity orderInstallmentsSaved = orderInstallmentsRepository.save(orderInstallmentsEntity);

            OrderInstallmentsResponseDTO orderInstallmentsResponseDTO = OrderInstallmentsResponseDTO.builder()
                    .id(orderInstallmentsSaved.getId())
                    .installment(orderInstallmentsSaved.getInstallment())
                    .numberOfInstallments(orderInstallmentsSaved.getNumberOfInstallments())
                    .paymentMethod(orderInstallmentsSaved.getPaymentMethod())
                    .build();

            orderInstallmentsEntityList.add(orderInstallmentsResponseDTO);
        });
    }



}

