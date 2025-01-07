package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.dto.CategoriasVeiculos;
import com.gerenciador.frota.aplicacao.gerenciador.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query(value = "select " +
            " ct.nome_categoria," +
            " COUNT(vc.placa_veiculo) as total_veiculos " +
            "from sc_gerenciador.tb_categoria ct " +
            "join sc_gerenciador.tb_veiculo vc on vc.categoria_id_id_categoria = ct.id_categoria " +
            "group by ct.nome_categoria", nativeQuery = true)
    List<CategoriasVeiculos> findCategoriasEVeiculos();
}
