package com.gerenciador.frota.aplicacao.autenticacao.infra.repository;

import com.gerenciador.frota.aplicacao.autenticacao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

    @Query(value = "SELECT * FROM sc_autenticacao.tb_usuario WHERE login_usuario = :login ", nativeQuery = true)
    Usuario findByLoginNativeQuery(@Param("login") String login);

    @Query(value = "SELECT * FROM sc_autenticacao.tb_usuario  \n" +
            "WHERE tb_usuario.status_usuario = true\n" +
            "AND LOWER(tb_usuario.nome_usuario) LIKE LOWER(CONCAT('%', :nomeUsuario, '%'))\n" +
            "AND LOWER(tb_usuario.email_usuario) LIKE LOWER(CONCAT('%', :emailUsuario, '%'))\n" +
            "ORDER BY tb_usuario.id_usuario DESC", nativeQuery = true)
    List<Usuario> findAllByNomeAndEmail(@Param("nomeUsuario") String nomeUsuario, @Param("emailUsuario") String emailUsuario);
}
