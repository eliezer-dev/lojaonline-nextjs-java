package dev.eliezer.lojaonline.integrations.pagarMe.mappers;

import dev.eliezer.lojaonline.integrations.pagarMe.Entity.PagarMeInvoicesEntity;
import dev.eliezer.lojaonline.integrations.pagarMe.dtos.PagarMeResponseDTO;
import dev.eliezer.lojaonline.integrations.pagarMe.payloads.FaturaPagarMeRequestPayload;
import dev.eliezer.lojaonline.integrations.pagarMe.payloads.FaturaPagarMeRequestPayload.PaymentSettingsDTO.CreditCardSettingsDTO.InstallmentDTO;
import dev.eliezer.lojaonline.integrations.pagarMe.payloads.FaturaPagarMeRequestPayload.CartSettingsDTO.Item;
import dev.eliezer.lojaonline.integrations.pagarMe.payloads.FaturaPagarMeResponsePayload;
import dev.eliezer.lojaonline.modules.order.dtos.CreateOrderDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FaturaPagarMeRequestMapper {

    public static FaturaPagarMeRequestPayload toFaturaPagarMeRequestPayload(CreateOrderDTO createOrderDTO) {
        FaturaPagarMeRequestPayload faturaPagarMeRequestPayload = new FaturaPagarMeRequestPayload();
        Integer pagarMeAmmount = createOrderDTO.getTotalValue().multiply(BigDecimal.valueOf(100)).intValue();

        List<InstallmentDTO> pagarMeInstallments = new ArrayList<>();

        faturaPagarMeRequestPayload.setName("Fatura Vegan Natu");

        if (createOrderDTO.getOrderInstallments() != null) {
            createOrderDTO.getOrderInstallments().forEach(installment -> {
                InstallmentDTO pagarMeInstallment = new InstallmentDTO(installment.getInstallment(),
                        installment.getInstallmentValue().multiply(BigDecimal.valueOf(100)).longValue());
                pagarMeInstallments.add(pagarMeInstallment);
            });

            faturaPagarMeRequestPayload.getPaymentSettings()
                    .getCreditCardSettings()
                    .setInstallments(pagarMeInstallments);


        }

        faturaPagarMeRequestPayload.getCartSettings()
                .setItems(List.of(new Item(pagarMeAmmount, "Pedido Vegan Natu", 1)));


        return faturaPagarMeRequestPayload;
    }


    public static PagarMeInvoicesEntity toPagarMeInvoicesEntity(FaturaPagarMeResponsePayload faturaPagarMeResponsePayload) {
       PagarMeInvoicesEntity pagarMeInvoice = new PagarMeInvoicesEntity();

        pagarMeInvoice.setIdFatura(faturaPagarMeResponsePayload.getIdFatura());
        pagarMeInvoice.setCreatedAt(faturaPagarMeResponsePayload.getCreatedAt());
        pagarMeInvoice.setPagarMeUrl(faturaPagarMeResponsePayload.getPagarMeUrl());
        pagarMeInvoice.setExpireIn(faturaPagarMeResponsePayload.getExpireIn());

        return pagarMeInvoice;
    }

    public static PagarMeResponseDTO toPagarMeResponseDTO (PagarMeInvoicesEntity pagarMeInvoice) {
        PagarMeResponseDTO pagarMeResponseDTO = new PagarMeResponseDTO();
        pagarMeResponseDTO.setIdFatura(pagarMeInvoice.getIdFatura());
        pagarMeResponseDTO.setUrl(pagarMeInvoice.getPagarMeUrl());

        return pagarMeResponseDTO;
    }

}
