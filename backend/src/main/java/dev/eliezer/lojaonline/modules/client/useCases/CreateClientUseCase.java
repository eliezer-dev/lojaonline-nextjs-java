package dev.eliezer.lojaonline.modules.client.useCases;

import dev.eliezer.lojaonline.exceptions.EmailFoundException;
import dev.eliezer.lojaonline.modules.client.dtos.CreateClientDTO;
import dev.eliezer.lojaonline.modules.client.dtos.ResponseClientDTO;
import dev.eliezer.lojaonline.modules.client.entities.ClientEntity;
import dev.eliezer.lojaonline.modules.client.mappers.ClientMapper;
import dev.eliezer.lojaonline.modules.client.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateClientUseCase {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientMapper clientMapper;

    public ResponseClientDTO execute(CreateClientDTO request) {

        clientRepository.findByEmail(request.getEmail())
                .ifPresent(clientSaved -> {
                    throw new EmailFoundException(clientSaved.getEmail());
                });


        ClientEntity clientToSave = clientMapper.toEntity(request);
        ClientEntity clientNew = clientRepository.save(clientToSave);

        return clientMapper.toResponseClientDTO(clientNew);


    }

}