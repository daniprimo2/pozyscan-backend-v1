package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.dto.FormaPagamento;
import com.gerenciador.frota.aplicacao.gerenciador.dto.NotaFiscalProjection.NotaFiscalProjection;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.NotaFiscalRespponse;
import com.gerenciador.frota.aplicacao.gerenciador.model.NotaFiscal;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, String> {
    List<NotaFiscal> findByFormaPagamentoAndNumero(FormaPagamento formaPagamento, String numeroNf);


    @Query(value = "SELECT nf.numero_nf AS numeroNf, " +
            "       nf.data_emissao AS dataEmissao, " +
            "       pc.descricao_parcela AS descricaoParcela, " +
            "       pc.data_vencimento AS dataDePagamento, " +
            "       pc.status_pagamento AS statusPagamento, " +
            "       round(CEIL(pc.valor_parcela::numeric * 100) / 100.0, 2) AS valorDoPagamento " +
            "FROM sc_gerenciador.tb_nota_fiscal nf " +
            "INNER JOIN sc_gerenciador.tb_parcelas pc ON nf.numero_nf = pc.nota_fiscal_numero_nf " +
            "WHERE nf.numero_nf = :numero", nativeQuery = true)
    List<NotaFiscalProjection> findByParcelasNotaFiscal(@Param("numero") String numero);

    @Modifying
    @Transactional
    @Query(value = "UPDATE sc_gerenciador.tb_nota_fiscal SET status_nota_fiscal = 'PAGO' WHERE numero_nf = :notaFiscal", nativeQuery = true)
    void updateStatusNotaFiscal(@Param("notaFiscal") String notaFiscal);
}
