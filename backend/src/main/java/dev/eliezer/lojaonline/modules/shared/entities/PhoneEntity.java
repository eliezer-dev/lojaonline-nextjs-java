package dev.eliezer.lojaonline.modules.shared.entities;

import dev.eliezer.lojaonline.modules.client.entities.ClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_phone")
public class PhoneEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String countryCode = "+55";

    @Column(nullable = false)
    private String areaCode;

    @Column(nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private ClientEntity client;


}
