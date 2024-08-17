package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.dto.response.LancamentoRelatorioResponse;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.ParcelaRelatorioResponse;
import com.gerenciador.frota.aplicacao.gerenciador.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query(value = "SELECT \n" +
            "    lc.id_lancamento,\n" +
            "    lc.centro_de_custo,\n" +
            "    nf.numero_nf,\n" +
            "    nf.data_emissao,\n" +
            "    nf.forma_pagamento,\n" +
            "    nf.valor_total,\n" +
            "    nf.status_nota_fiscal,\n" +
            "    COUNT(pc.id) AS parcelas_a_pagar,\n" +
            "    SUM(CASE WHEN pc.status_pagamento = 'PAGO' THEN 1 ELSE 0 END) AS parcelas_pagas,\n" +
            "    pv.data_vencimento AS venc_prox_parcela,\n" +
            "    pv.descricao_parcela AS status_parcela,\n" +
            "    ROUND(CEIL(pv.valor_parcela::NUMERIC * 100) / 100.0, 2) AS valor_parcela\n" +
            "FROM sc_gerenciador.tb_nota_fiscal nf\n" +
            "INNER JOIN sc_gerenciador.tb_lancamento lc ON nf.numero_nf = lc.numero_nf_numero_nf\n" +
            "LEFT JOIN sc_gerenciador.tb_parcelas pc ON nf.numero_nf = pc.nota_fiscal_numero_nf\n" +
            "LEFT JOIN (\n" +
            "    SELECT \n" +
            "        p1.nota_fiscal_numero_nf,\n" +
            "        p1.data_vencimento,\n" +
            "        p1.descricao_parcela,\n" +
            "        p1.valor_parcela\n" +
            "    FROM sc_gerenciador.tb_parcelas p1\n" +
            "    INNER JOIN (\n" +
            "        SELECT \n" +
            "            nota_fiscal_numero_nf,\n" +
            "            MIN(data_vencimento) AS min_data_vencimento\n" +
            "        FROM sc_gerenciador.tb_parcelas\n" +
            "        WHERE status_pagamento = 'EM_ABERTO'\n" +
            "        GROUP BY nota_fiscal_numero_nf\n" +
            "    ) p2 ON p1.nota_fiscal_numero_nf = p2.nota_fiscal_numero_nf\n" +
            "    AND p1.data_vencimento = p2.min_data_vencimento\n" +
            "    WHERE p1.status_pagamento = 'EM_ABERTO'\n" +
            ") pv ON nf.numero_nf = pv.nota_fiscal_numero_nf\n" +
            "WHERE LOWER(nf.numero_nf) LIKE LOWER(CONCAT('%', :numeroNf, '%'))\n" +
            "AND LOWER(nf.status_nota_fiscal) LIKE LOWER(CONCAT('%', :statusNotaFiscal, '%'))\n" +
            "GROUP BY \n" +
            "    lc.id_lancamento,\n" +
            "    lc.centro_de_custo,\n" +
            "    nf.numero_nf,\n" +
            "    nf.data_emissao,\n" +
            "    nf.forma_pagamento,\n" +
            "    nf.valor_total,\n" +
            "    nf.status_nota_fiscal,\n" +
            "    pv.data_vencimento,\n" +
            "    pv.valor_parcela,\n" +
            "    pv.descricao_parcela\n" +
            "ORDER BY \n" +
            "    CASE nf.status_nota_fiscal\n" +
            "        WHEN 'EM_ABERTO' THEN 1\n" +
            "\t\tWHEN 'AVISTA_A_PRAZO' THEN 2\n" +
            "        WHEN 'PAGO' THEN 3\n" +
            "    END,\n" +
            "    EXTRACT(YEAR FROM TO_DATE(pv.data_vencimento, 'DD/MM/YYYY')) DESC,\n" +
            "    EXTRACT(MONTH FROM TO_DATE(pv.data_vencimento, 'DD/MM/YYYY')) DESC,\n" +
            "    EXTRACT(DAY FROM TO_DATE(pv.data_vencimento, 'DD/MM/YYYY')) DESC", nativeQuery = true)
    List<LancamentoRelatorioResponse> findByRelatorioLancamento(@Param("numeroNf") String numeroNf,
                                                                @Param("statusNotaFiscal") String statusNotaFiscal);
    @Query(value = "SELECT lc.id_lancamento,\n" +
            "       lc.centro_de_custo,\n" +
            "\t   pc.nota_fiscal_numero_nf,\n" +
            "\t   pc.data_vencimento,\n" +
            "\t   pc.descricao_parcela,\n" +
            "\t   pc.status_pagamento,\n" +
            "\t   \t   ROUND(CEIL(pc.valor_parcela::NUMERIC * 100) / 100.0, 2) AS valor_parcela\n" +
            "FROM sc_gerenciador.tb_parcelas pc\n" +
            "INNER JOIN sc_gerenciador.tb_nota_fiscal nf ON pc.nota_fiscal_numero_nf = nf.numero_nf\n" +
            "INNER JOIN sc_gerenciador.tb_lancamento lc ON lc.numero_nf_numero_nf = nf.numero_nf\n" +
            "WHERE LOWER(lc.centro_de_custo) LIKE LOWER(CONCAT('%', :centroDeCusto , '%'))\n" +
            "AND LOWER(pc.nota_fiscal_numero_nf) LIKE LOWER(CONCAT('%', :numeroNotaFiscal , '%'))\n" +
            "AND LOWER(pc.data_vencimento) LIKE LOWER(CONCAT('%', :dataVencimento , '%'))\n" +
            "AND LOWER(pc.status_pagamento) LIKE LOWER(CONCAT('%', :statusPagamentos , '%'))\n" +
            "ORDER BY \n" +
            "\t\t case pc.status_pagamento\n" +
            "\t\t \twhen 'EM_ABERTO' THEN 1\n" +
            "\t\t\tWHEN 'PAGO' THEN 2\n" +
            "\t\t end,\n" +
            "\t\t EXTRACT(YEAR FROM TO_DATE(pc.data_vencimento, 'DD/MM/YYYY')) ASC,\n" +
            "         EXTRACT(MONTH FROM TO_DATE(pc.data_vencimento, 'DD/MM/YYYY')) ASC,\n" +
            "\t\t EXTRACT(DAY FROM TO_DATE(pc.data_vencimento, 'DD/MM/YYYY')) ASC", nativeQuery = true)
    List<ParcelaRelatorioResponse> findByRelatorioParcelas(@Param("centroDeCusto") String centroDeCusto,
                                                           @Param("numeroNotaFiscal") String numeroNotaFiscal,
                                                           @Param("dataVencimento") String dataVencimento,
                                                           @Param("statusPagamentos") String statusPagamentos);

    @Query(value = "DELETE FROM sc_gerenciador.tb_parcelas where nota_fiscal_numero_nf = :notaFiscal", nativeQuery = true)
    void deletarTodosAsParcelasExistentes(@Param("notaFiscal") Long notaFiscal);
}
