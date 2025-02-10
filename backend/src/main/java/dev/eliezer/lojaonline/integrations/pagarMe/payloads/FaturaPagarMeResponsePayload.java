package dev.eliezer.lojaonline.integrations.pagarMe.payloads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaturaPagarMeResponsePayload {
    @JsonProperty("order_code")
    String idFatura;

    @JsonProperty("expires_in")
    Integer expireIn;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("url")
    String pagarMeUrl;

}
