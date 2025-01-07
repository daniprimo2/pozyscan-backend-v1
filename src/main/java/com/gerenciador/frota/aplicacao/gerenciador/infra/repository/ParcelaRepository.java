package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.dto.response.ParcelaInfoResponse;
import com.gerenciador.frota.aplicacao.gerenciador.model.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {


    @Query(value = "DELETE FROM sc_gerenciador.tb_parcelas where nota_fiscal_numero_nf = :notaFiscal", nativeQuery = true)
    void deletarTodosAsParcelasExistentes(@Param("notaFiscal") Long notaFiscal);

    @Modifying
    @Transactional
    @Query(value = "update sc_gerenciador.tb_parcelas set status_pagamento = 'PAGO' where id = :id", nativeQuery = true)
    void registrarPagamento(@Param("id") Long id);

    /*@Query(value = "SELECT EXISTS (\n" +
            "    SELECT 1\n" +
            "    FROM sc_gerenciador.tb_parcelas\n" +
            "    WHERE status_pagamento = 'EM_ABERTO'\n" +
            "      AND nota_fiscal_numero_nf = :numeroNotaFiscal\n" +
            ")", nativeQuery = true)
    boolean isTemParcelaParaSerPaga(@Param("numeroNotaFiscal") String numeroNotaFiscal);
*/
    @Query(value = "SELECT" +
            " pc.id," +
            " pc.status_pagamento," +
            " pc.nota_fiscal_numero_nf as nota_fiscal" +
            " FROM sc_gerenciador.tb_parcelas pc " +
            "WHERE pc.status_pagamento = 'EM_ABERTO' " +
            "AND pc.nota_fiscal_numero_nf  = :numeroNotaFiscal", nativeQuery = true)
    List<ParcelaInfoResponse> isTemParcelaParaSerPaga(@Param("numeroNotaFiscal") String numeroNotaFiscal);


}
