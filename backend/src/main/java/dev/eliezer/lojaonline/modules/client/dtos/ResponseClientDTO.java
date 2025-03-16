package dev.eliezer.lojaonline.modules.client.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})

public class ResponseClientDTO extends ClientDTO{

    @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED, description = "client id")
    @JsonProperty("id")
    private Long id;

}
