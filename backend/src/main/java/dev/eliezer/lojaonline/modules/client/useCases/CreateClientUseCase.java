package dev.eliezer.lojaonline.modules.client.useCases;

import dev.eliezer.lojaonline.exceptions.EmailFoundException;
import dev.eliezer.lojaonline.modules.client.dtos.CreateClientDTO;
import dev.eliezer.lojaonline.modules.client.dtos.CreateResponseClientDTO;
import dev.eliezer.lojaonline.modules.client.entities.ClientEntity;
import dev.eliezer.lojaonline.modules.client.mappers.ClientMapper;
import dev.eliezer.lojaonline.modules.client.repositories.ClientRepository;
import dev.eliezer.lojaonline.modules.shared.entities.UserToken;
import dev.eliezer.lojaonline.providers.JWTUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CreateClientUseCase {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private JWTUserProvider jwtUserProvider;

    public CreateResponseClientDTO execute(CreateClientDTO request) {

        clientRepository.findByEmail(request.getEmail())
                .ifPresent(clientSaved -> {
                    throw new EmailFoundException(clientSaved.getEmail());
                });

        ClientEntity clientSaved = clientRepository.save(clientMapper.toEntity(request));

        CreateResponseClientDTO response = clientMapper.toResponseClientDTO(clientSaved);

        response.setClientToken(jwtUserProvider.tokenGenerator(response.getId().toString(), Collections.singletonList(1L)));

        return response;


    }

}