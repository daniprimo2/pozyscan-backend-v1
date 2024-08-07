package com.gerenciador.frota.aplicacao.autenticacao.controller;

import com.gerenciador.frota.aplicacao.autenticacao.dto.RetornoServiceBaseDTO;
import com.gerenciador.frota.aplicacao.autenticacao.dto.request.FiltroUsuariosRquest;
import com.gerenciador.frota.aplicacao.autenticacao.dto.request.UsuarioRequest;
import com.gerenciador.frota.aplicacao.autenticacao.infra.service.UsuarioService;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public RetornoServiceBaseDTO addUsuario(@RequestBody UsuarioRequest usuario) {
        return usuarioService.adicionarUsuario(usuario);
    }

    @PostMapping("/busca/filtro")
    public PageImpl<?> buscarUsuariosComFiltro(@RequestBody FiltroUsuariosRquest filtroRquest,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioService.buscarUsuarioComFiltro(filtroRquest, pageable);
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarUsuariosPorId(@PathVariable Long id) {
        return usuarioService.deletarUsuarioDeId(id);
    }
}
