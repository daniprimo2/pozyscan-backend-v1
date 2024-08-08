package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.model.Aplicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AplicacaoRepository extends JpaRepository<Aplicacao, Long> {

    @Query(value = "SELECT * FROM sc_gerenciador.tb_aplicacao \n" +
            "WHERE LOWER(tb_aplicacao.tipo_nome) LIKE LOWER(CONCAT('%', :tipo , '%'))\n" +
            "AND LOWER(tb_aplicacao.descricao) LIKE LOWER(CONCAT('%', :descricao , '%'))\n" +
            "ORDER BY tb_aplicacao.id_aplicacao DESC", nativeQuery = true)
    List<Aplicacao> findAllByTipoAndDescricaoAndId(@Param("tipo") String tipo, @Param("descricao") String descricao);


}
