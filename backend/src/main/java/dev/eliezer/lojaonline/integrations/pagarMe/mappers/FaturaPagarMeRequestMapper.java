package dev.eliezer.lojaonline.integrations.pagarMe.mappers;

import dev.eliezer.lojaonline.integrations.pagarMe.Entity.PagarMeInvoicesEntity;
import dev.eliezer.lojaonline.integrations.pagarMe.dtos.PagarMeResponseDTO;
import dev.eliezer.lojaonline.integrations.pagarMe.payloads.FaturaPagarMeRequestPayload;
import dev.eliezer.lojaonline.integrations.pagarMe.payloads.FaturaPagarMeRequestPayload.Item;
import dev.eliezer.lojaonline.integrations.pagarMe.payloads.FaturaPagarMeResponsePayload;
import dev.eliezer.lojaonline.modules.order.dtos.CreateOrderDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FaturaPagarMeRequestMapper {

    public static FaturaPagarMeRequestPayload toFaturaPagarMeRequestPayload(CreateOrderDTO createOrderDTO) {
        FaturaPagarMeRequestPayload faturaPagarMeRequestPayload = new FaturaPagarMeRequestPayload();

        List<Item> items = new ArrayList<>();

        Integer pagarMeAmmount = createOrderDTO.getTotalValue().multiply(BigDecimal.valueOf(100)).intValue();
        createOrderDTO.getOrderItems().forEach(orderItem -> {
            Item item = new Item();
            item.setAmount(orderItem.getPrice().multiply(BigDecimal.valueOf(100)).intValue());
            item.setQuantity(orderItem.getQuantity().intValue());
            item.setCode(orderItem.getProductId().toString());
            item.setDescription("teste");
            items.add(item);
        });
        faturaPagarMeRequestPayload.setItems(items);
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
