package dev.eliezer.lojaonline.modules.auth.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.eliezer.lojaonline.exceptions.UsernameNotFoundException;
import dev.eliezer.lojaonline.modules.auth.dto.AuthUserRequestDTO;
import dev.eliezer.lojaonline.modules.auth.dto.AuthUserResponseDTO;
import dev.eliezer.lojaonline.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

@Service
public class AuthUserUseCase{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret-user}")
    private String secretKey;

    public AuthUserResponseDTO execute (AuthUserRequestDTO userAuth) {

        var user = userRepository.findByEmail(userAuth.getEmail())
                .orElseThrow(() -> {
                            throw new UsernameNotFoundException();
                        }

                );

        var passwordMatches = passwordEncoder.matches(userAuth.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new UsernameNotFoundException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("LojaOnline")
                .withSubject(user.getId().toString())
                .withClaim("roles", Collections.singletonList(user.getUserRole()))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authUserResponse = AuthUserResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authUserResponse;
    }



}

