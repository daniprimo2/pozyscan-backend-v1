package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.model.Filial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {

    @Query(value = "SELECT * FROM sc_gerenciador.tb_filial\n" +
            "WHERE LOWER(tb_filial.patente) LIKE LOWER(CONCAT('%', :patente, '%'))\n" +
            "AND LOWER(tb_filial.nome_filial) LIKE LOWER(CONCAT('%', :nome, '%'))\n" +
            "AND LOWER(tb_filial.centro_de_custo) LIKE LOWER(CONCAT('%', :centroDeCusto, '%'))\n" +
            "ORDER BY tb_filial.id_filial DESC", nativeQuery = true)
    List<Filial> findAllByNomeAndCentroDeCustoAndPatente(@Param("nome") String nome,
                                                         @Param("centroDeCusto") String centroDeCusto,
                                                         @Param("patente") String patente);
}
