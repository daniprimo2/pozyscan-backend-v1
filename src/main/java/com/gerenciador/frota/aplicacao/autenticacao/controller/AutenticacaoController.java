package com.gerenciador.frota.aplicacao.autenticacao.controller;

import com.gerenciador.frota.aplicacao.autenticacao.dto.request.CredenciaisDTO;
import com.gerenciador.frota.aplicacao.autenticacao.dto.request.RequestRefreshToken;
import com.gerenciador.frota.aplicacao.autenticacao.dto.response.LoginResponseDTO;
import com.gerenciador.frota.aplicacao.autenticacao.infra.service.TokenService;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Gestão de Acesso", description = "Controladores de de cadastro de usuarios e autenticação")
@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid CredenciaisDTO credenciaisDTO) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(credenciaisDTO.login(), credenciaisDTO.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            System.out.println(auth.toString());
            var token = tokenService.obterToken(credenciaisDTO);

            return ResponseEntity.ok(new LoginResponseDTO(token.token(), token.refreshToken()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(RetornoServicoBase.negativo("Usuario não registrado."));
        }
    }
    @PostMapping("/refreshToken")
    public ResponseEntity login(@RequestBody @Valid RequestRefreshToken refreshToken) {
        try {
            LoginResponseDTO token;
            token = this.tokenService.obterRefresfToken(refreshToken.regreshToken());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            throw new RuntimeException("Erro na autenticação");
        }
    }

}
