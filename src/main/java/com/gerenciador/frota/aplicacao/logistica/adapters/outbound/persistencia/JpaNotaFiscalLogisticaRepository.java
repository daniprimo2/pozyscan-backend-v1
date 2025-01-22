package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaNotaFiscalLogisticaRepository extends JpaRepository<JpaNotaFiscalLogisticaEntity, Long> {
}
