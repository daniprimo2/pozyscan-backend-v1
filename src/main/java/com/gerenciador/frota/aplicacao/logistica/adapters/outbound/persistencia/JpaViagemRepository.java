package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<JpaViagemEntity, Long> {

    @Query(value = "select * from sc_logistica.tb_viagem v where LOWER(v.data_programada_viagem) LIKE LOWER(CONCAT('%', :dataProgramada, '%')) AND LOWER(v.tipo_viagem) LIKE LOWER(CONCAT('%', :tipoViagem, '%'))", nativeQuery = true)
    List<JpaViagemEntity> findAllFiltro(@Param("dataProgramada") String dataProgramada, @Param("tipoViagem") TipoViagem tipoViagem);
}
