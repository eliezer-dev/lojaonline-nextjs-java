package dev.eliezer.lojaonline.modules.client.entities;

import dev.eliezer.lojaonline.modules.shared.entities.AddressEntity;
import dev.eliezer.lojaonline.modules.shared.entities.PhoneEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name = "tb_client")
public class ClientEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;


 }
