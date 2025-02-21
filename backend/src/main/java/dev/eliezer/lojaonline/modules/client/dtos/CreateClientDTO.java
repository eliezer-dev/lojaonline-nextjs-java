package dev.eliezer.lojaonline.modules.client.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.eliezer.lojaonline.modules.client.entities.ClientEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClientDTO {
    @NotBlank(message = "fullname not provided")
    @Schema(example = "Jose da Silva", requiredMode = Schema.RequiredMode.REQUIRED, description = "client full name")
    private String name;

    @NotBlank (message = "email not provided")
    @Email(message = "invalid email")
    @Schema(example = "jose@email.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "client email")
    private String email;

    @NotBlank (message = "document not provided")
    @Schema(example = "123456789123", requiredMode = Schema.RequiredMode.REQUIRED, description = "client document")
    private String document;

    @NotBlank(message = "gender not provided")
    @jakarta.validation.constraints.Pattern(regexp = "^(male|female)$", message = "gender must be either 'male' or 'female'")
    @Schema(example = "male/female", requiredMode = Schema.RequiredMode.REQUIRED, description = "client gender")
    private String gender;

    @NotBlank (message = "gender not provided")
    @Schema(example = "male/female", requiredMode = Schema.RequiredMode.REQUIRED, description = "client gender")
    private LocalDate birthDate;

    @NotBlank(message = "password not provided")
    @jakarta.validation.constraints.Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "password must be at least 8 characters long and contain both letters and numbers")
    @Schema(example = "Senha1234@@", requiredMode = Schema.RequiredMode.REQUIRED, description = "client password")
    private String password;
    
    @NotEmpty (message = "phone not provided")
    private List<PhoneDTO> phone;
    
    @NotNull (message = "address not provided")
    private AddressDTO address;

    public class PhoneDTO {

        @Schema(example = "+55", requiredMode = Schema.RequiredMode.REQUIRED, description = "client country code")
        private String countryCode = "+55";

        @Schema(example = "19", requiredMode = Schema.RequiredMode.REQUIRED, description = "client area code")
        @Column(nullable = false)
        private String areaCode;

        @Schema(example = "987654321", requiredMode = Schema.RequiredMode.REQUIRED, description = "client phone number")
        private String number;
        
    }

    public class AddressDTO {

        @Schema(example = "BR", requiredMode = Schema.RequiredMode.REQUIRED, description = "country name")
        @NotBlank(message = "country not provided")
        @jakarta.validation.constraints.Pattern(regexp = "^[A-Z]{2}$", message = "country must be a valid two-letter code")
        private String country;

        @Schema(example = "SP", requiredMode = Schema.RequiredMode.REQUIRED, description = "state name")
        @NotBlank(message = "state not provided")
        @jakarta.validation.constraints.Pattern(regexp = "^[A-Z]{2}$", message = "state must be a valid two-letter code")
        private String state;

        @Schema(example = "Campinas", requiredMode = Schema.RequiredMode.REQUIRED, description = "city name")
        @NotBlank(message = "city not provided")
        private String city;

        @Schema(example = "13083-970", requiredMode = Schema.RequiredMode.REQUIRED, description = "postal zip code")
        @NotBlank(message = "zip code not provided")
        @jakarta.validation.constraints.Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "zip code must follow the format XXXXX-XXX")
        private String zipCode;

        @Schema(example = "Centro", requiredMode = Schema.RequiredMode.REQUIRED, description = "neighborhood description")
        @NotBlank(message = "neighborhood not provided")
        private String neighborhood;

        @Schema(example = "Av. Andrade Neves", requiredMode = Schema.RequiredMode.REQUIRED, description = "street name")
        @NotBlank(message = "street not provided")
        private String street;

        @Schema(example = "123", requiredMode = Schema.RequiredMode.REQUIRED, description = "address number")
        @NotBlank(message = "number not provided")
        private String number;

        @Schema(example = "Apartment 45B", description = "optional complement information")
        @NotBlank(message = "complement not provided")
        private String complement;
    
    }

}
