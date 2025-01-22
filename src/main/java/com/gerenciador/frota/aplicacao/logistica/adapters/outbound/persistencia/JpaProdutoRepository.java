package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProdutoRepository extends JpaRepository<JpaProdutoEntity, Long> {


}
