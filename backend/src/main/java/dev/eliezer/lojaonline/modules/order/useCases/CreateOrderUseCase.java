package dev.eliezer.lojaonline.modules.order.useCases;

import dev.eliezer.lojaonline.exceptions.BusinessException;
import dev.eliezer.lojaonline.exceptions.NotFoundException;
import dev.eliezer.lojaonline.modules.order.dtos.*;
import dev.eliezer.lojaonline.modules.order.entities.*;
import dev.eliezer.lojaonline.modules.order.repositories.*;
import dev.eliezer.lojaonline.modules.product.entities.ProductEntity;
import dev.eliezer.lojaonline.modules.product.repositories.ProductRepository;
import dev.eliezer.lojaonline.modules.user.entities.UserEntity;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    private List<OrderItemResponseDTO> orderItemResponseDTOList;

    private List<OrderInstallmentsResponseDTO> orderInstallmentsResponseDTOList;


    public OrderResponseDTO execute (CreateOrderDTO dataReq) {

        this.createOrderDTO = dataReq;
        this.user = userRepository.findById(dataReq.getUserId()).orElseThrow(() -> new NotFoundException(createOrderDTO.getUserId()));

        orderValidator ();

        saveOrder ();

        saveOrderItem();

        saveOrderInstallments ();

        return new OrderResponseDTO(orderEntity, orderItemResponseDTOList, orderInstallmentsResponseDTOList);

    }

    public void saveOrder () {
           orderEntity = orderRepository.save(new OrderEntity(user, createOrderDTO));
    }

    public void saveOrderItem() {
        orderItemResponseDTOList = new ArrayList<>();

        createOrderDTO.getOrderItems().forEach(item -> {

            ProductEntity productEntity = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new NotFoundException(item.getProductId()));

            OrderItemEntity orderItemSaved = orderItemRepository
                    .save(new OrderItemEntity(orderEntity, item, productEntity));

            orderItemResponseDTOList.add(new OrderItemResponseDTO(orderItemSaved));
        });
    }

    public void saveOrderInstallments () {
        orderInstallmentsResponseDTOList = new ArrayList<>();

        createOrderDTO.getOrderInstallments().forEach(createOrderInstallmentsDTO -> {

            OrderInstallmentsEntity orderInstallmentsSaved = orderInstallmentsRepository
                    .save(new OrderInstallmentsEntity(orderEntity, createOrderInstallmentsDTO));

            orderInstallmentsResponseDTOList.add(new OrderInstallmentsResponseDTO(orderInstallmentsSaved));
        });
    }

    public void orderValidator () {
        BigDecimal sumTotalValue = BigDecimal.valueOf(0.00);

        for (CreateOrderItemDTO orderItem : createOrderDTO.getOrderItems()) {
            BigDecimal totalValueItem = orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            sumTotalValue = sumTotalValue.add(totalValueItem);

        }

        if (sumTotalValue.compareTo(createOrderDTO.getTotalValue()) != 0) {
            throw new BusinessException("Total Value different from the sum of items");
        }
    }


}

