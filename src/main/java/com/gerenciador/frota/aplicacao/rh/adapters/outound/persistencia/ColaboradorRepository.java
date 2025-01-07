package com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
}
