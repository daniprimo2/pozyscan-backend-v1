package com.gerenciador.frota.aplicacao.autenticacao.infra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gerenciador.frota.aplicacao.autenticacao.dto.request.CredenciaisDTO;
import com.gerenciador.frota.aplicacao.autenticacao.dto.response.LoginResponseDTO;
import com.gerenciador.frota.aplicacao.autenticacao.infra.repository.UsuarioRepository;
import com.gerenciador.frota.aplicacao.autenticacao.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    @Value("${api.security.refresh-token.expirations}")
    private Integer expirationRefreshSecret;
    @Value("${api.security.token.expirations}")
    private Integer expirationToken;


    @Autowired
    private UsuarioRepository usuarioRepository;

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api-pmi")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }
    public LoginResponseDTO obterToken(CredenciaisDTO authenticationDTO){
        Usuario usuario = usuarioRepository.findByLoginNativeQuery(authenticationDTO.login());

        return LoginResponseDTO.builder()
                .token(geradorToken(usuario, expirationToken))
                .refreshToken(geradorToken(usuario, expirationRefreshSecret))
                .build();
    }
    private Instant genExpirationDate(Integer expiration){
        return LocalDateTime.now().plusHours(expiration).toInstant(ZoneOffset.of("-03:00"));
    }

    public String geradorToken(Usuario usuario, Integer expiration) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("api-pmi")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(genExpirationDate(expiration))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error ao gerar token", exception);
        }
    }


    public LoginResponseDTO obterRefresfToken(String token) throws Exception {

        String login = validateToken(token);
        System.out.println(login);

        Usuario user = usuarioRepository.findByLoginNativeQuery(login);
        System.out.println(user);


        if (user.getId() != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        if (user.getId() == null) {

            throw new Exception("Token não autorizado");
        }


        return LoginResponseDTO.builder()
                .token(geradorToken(user, expirationToken))
                .refreshToken(geradorToken(user, expirationRefreshSecret))
                .build();

    }
}
