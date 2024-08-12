package com.gerenciador.frota.aplicacao.gerenciador.infra.repository;

import com.gerenciador.frota.aplicacao.gerenciador.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
