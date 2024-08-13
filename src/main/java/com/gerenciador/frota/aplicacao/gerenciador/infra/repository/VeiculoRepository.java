package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {

    @Query(value = "WITH TipoPresente AS (\n" +
            "    SELECT COUNT(*) AS qtd\n" +
            "    FROM sc_gerenciador.tb_veiculo\n" +
            "    WHERE tipo_id_id_tipo = :tipo\n" +
            "),\n" +
            "CategoriaPresente AS (\n" +
            "    SELECT COUNT(*) AS qtd\n" +
            "    FROM sc_gerenciador.tb_veiculo\n" +
            "    WHERE categoria_id_id_categoria = :categoria\n" +
            ")\n" +
            "SELECT * \n" +
            "FROM sc_gerenciador.tb_veiculo\n" +
            "WHERE LOWER(tb_veiculo.modelo_veiculo) LIKE LOWER(CONCAT('%', :modelo , '%'))\n" +
            "AND LOWER(tb_veiculo.placa_Veiculo) LIKE LOWER(CONCAT('%', :placa , '%'))\n" +
            "AND (\n" +
            "    (SELECT qtd FROM TipoPresente) > 0 AND tb_veiculo.tipo_id_id_tipo = :tipo\n" +
            "    OR (SELECT qtd FROM TipoPresente) = 0 AND tb_veiculo.tipo_id_id_tipo IS NOT NULL\n" +
            ")\n" +
            "AND (\n" +
            "    (SELECT qtd FROM CategoriaPresente) > 0 AND tb_veiculo.categoria_id_id_categoria = :categoria\n" +
            "    OR (SELECT qtd FROM CategoriaPresente) = 0 AND tb_veiculo.categoria_id_id_categoria IS NOT NULL\n" +
            ");", nativeQuery = true)
    List<Veiculo> findByPlacaAndModeloAndTipoAndCategoria(@Param("placa") String placa,
                                                          @Param("modelo") String modelo,
                                                          @Param("tipo") Long tipo,
                                                          @Param("categoria") Long categoria);
}
