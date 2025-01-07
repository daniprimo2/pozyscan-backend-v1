package com.gerenciador.frota.aplicacao.autenticacao.infra.service;


import com.gerenciador.frota.aplicacao.Util.Html.UtilHtml;
import com.gerenciador.frota.aplicacao.Util.Utils.UtilPaginacao;
import com.gerenciador.frota.aplicacao.autenticacao.dto.ParametrosEmailRequest;
import com.gerenciador.frota.aplicacao.autenticacao.dto.RetornoServiceBaseDTO;
import com.gerenciador.frota.aplicacao.autenticacao.dto.email.EmailService;
import com.gerenciador.frota.aplicacao.autenticacao.dto.request.FiltroUsuariosRquest;
import com.gerenciador.frota.aplicacao.autenticacao.dto.request.UsuarioRequest;
import com.gerenciador.frota.aplicacao.autenticacao.infra.repository.UsuarioRepository;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.autenticacao.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    public RetornoServiceBaseDTO adicionarUsuario (UsuarioRequest usuarioRequest) {
        try {
            log.info("[START] - Adicionando um novo usuario.");
            var usuario = usuarioRequest.construirUsuario();
            var usuarioSalvo = usuarioRepository.save(usuario);
            var login = gerarAcessoLogin(usuarioSalvo.getId());
            usuario.setLogin(login);
            usuario.setPassword(encryptografarSenha(login));
            var usuarioSalvoLogin = usuarioRepository.save(usuario);

            var corpoEmail = UtilHtml.emailHtmlToString("/templates/email/emailCredenciais.html",
                    UtilHtml.construirParametrosHtml(ParametrosEmailRequest.costruirParametrosCom(usuarioSalvo)));
            emailService.enviarEmail("Credências de acesso ao POZYSCAN",
                     corpoEmail,
                    usuario.getEmail());
            log.info("[END] - Usuario foi adicionado com sucesso.");
            return RetornoServiceBaseDTO.retornoPositivo("Usuario "+usuarioSalvoLogin.getLogin()+" salvo com sucesso.");
        } catch (Exception e) {
            log.info("[ERRO] - Usuario não foi adicionado. " + e.getMessage());
            return RetornoServiceBaseDTO.retornoNegativos("Usuario não foi salvo. " + e.getMessage());
        }
    }

    private String encryptografarSenha(String login) {
        return new BCryptPasswordEncoder().encode(login);
    }

    private String gerarAcessoLogin(Long id) {
        StringBuilder concat = new StringBuilder();
        concat.append("filial");
        concat.append(String.format("%04d", id));
        return concat.toString();
    }


    public PageImpl<?> buscarUsuarioComFiltro(FiltroUsuariosRquest filtroRquest, Pageable pageable) {
        List<Usuario> usuarios = this.getUsuarios(filtroRquest);
        return UtilPaginacao.obterPaginacao(usuarios, pageable);
    }

    private List<Usuario> getUsuarios(FiltroUsuariosRquest filtroRquest) {
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        if (!filtroRquest.getIdUsuario().isEmpty()) {
            log.info("[INFO] - Filtro pelo id do usuario concluido.");
            listaDeUsuarios.add(usuarioRepository.findById(Long.valueOf(filtroRquest.getIdUsuario()))
                    .orElseThrow(() -> new RuntimeException("Id do usuario não encontrado.")));
        } else {
            log.info("[INFO] - Filtro pelas demais informações concluido.");
            listaDeUsuarios.addAll(usuarioRepository.findAllByNomeAndEmail(filtroRquest.getNomeUsuario(),
                    filtroRquest.getEmailUsuario()));
        }

        return listaDeUsuarios;

    }

    public RetornoServicoBase deletarUsuarioDeId(Long id) {
        log.info("[START] - Deletando usuario.");
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado."));
        usuarioRepository.delete(usuario);
        log.info("[END] - Usuario deletado com sucesso.");
        return RetornoServicoBase.positivo("Usuario " + usuario.getNome() + " excluido com sucesso.");
    }
}
