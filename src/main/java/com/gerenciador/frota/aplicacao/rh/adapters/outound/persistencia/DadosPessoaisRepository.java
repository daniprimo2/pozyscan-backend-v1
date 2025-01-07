package com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosPessoaisRepository extends JpaRepository<DadosPessoaisEntity, Long> {
}
