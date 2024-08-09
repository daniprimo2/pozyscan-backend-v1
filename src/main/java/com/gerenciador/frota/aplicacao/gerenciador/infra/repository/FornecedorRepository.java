package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    @Query(value = "SELECT * FROM sc_gerenciador.tb_fornecedor\n" +
            "where lower(tb_fornecedor.cnpj_fornecedor) LIKE LOWER(CONCAT('%', :cnpj , '%'))\n" +
            "AND lower(tb_fornecedor.nome_fornecedor) LIKE LOWER(CONCAT('%', :nome , '%'))\n" +
            "order by tb_fornecedor.id_fornecedor desc", nativeQuery = true)
    List<Fornecedor> findAllByCnpjAndNome(@Param("nome") String nome, @Param("cnpj") String cnpj);

    @Transactional
    @Modifying
    @Query(value = "CALL sc_gerenciador.procedure_deletar_fornecedor_e_seus_contatos(CAST(:id AS bigint))", nativeQuery = true)
    void deletarFornecedor(@Param("id") Long id);
}
