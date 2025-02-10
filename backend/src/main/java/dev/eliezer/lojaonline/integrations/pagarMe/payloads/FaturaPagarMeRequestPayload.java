package dev.eliezer.lojaonline.integrations.pagarMe.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class FaturaPagarMeRequestPayload {

    @JsonProperty("is_building")
    private Boolean isBuilding = false;

    private String name = "Pedido Vegan Natu";

    private String type = "order";

    @JsonProperty("payment_settings")
    private PaymentSettingsDTO paymentSettings = new PaymentSettingsDTO();

    @JsonProperty("cart_settings")
    private CartSettingsDTO cartSettings = new CartSettingsDTO();


    @Data
    public static class PaymentSettingsDTO {

        @JsonProperty("credit_card_settings")
        private CreditCardSettingsDTO creditCardSettings = new CreditCardSettingsDTO();

        @JsonProperty("accepted_payment_methods")
        private List<String> acceptedPaymentMethods = List.of("credit_card");

        @Data
        public static class CreditCardSettingsDTO {
            @JsonProperty("operation_type")
            private String operationType = "auth_and_capture";

            private List<InstallmentDTO> installments;

            @Data
            public static class InstallmentDTO {
                private Long number;

                private Long total;

                public InstallmentDTO(Long number, Long total) {
                    this.number = number;
                    this.total = total;
                }
            }
        }

    }

    @Data
    public static class CartSettingsDTO {

        // Campos fixos
        private String name = "Pedido Vegan Natu";
        private String type = "order";

        // Array de objetos fixos
//        private List<Item> items = List.of(
//                new Item(12000, "Pedido Vegan Natu", 1)
//        );

        private List<Item> items = new ArrayList<>();
        // Classe interna para representar um item
        @Data
        public static class Item {
            private Integer amount;
            private String name;

            @JsonProperty("default_quantity")
            private Integer defaultQuantity;

            public Item(Integer amount, String name, Integer defaultQuantity) {
                this.amount = amount;
                this.name = name;
                this.defaultQuantity = defaultQuantity;
            }
        }
    }


}
