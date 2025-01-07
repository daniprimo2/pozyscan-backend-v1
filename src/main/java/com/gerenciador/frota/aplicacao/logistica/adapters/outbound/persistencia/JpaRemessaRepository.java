package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemessaRepository extends JpaRepository<JpaRemessaEntity, Long> {
    @Query(value = "SELECT * FROM sc_logistica.tb_remessa rm where rm.status_remessa = :status", nativeQuery = true)
    List<JpaRemessaEntity> findAllStatus(@Param("status") String status);
}
