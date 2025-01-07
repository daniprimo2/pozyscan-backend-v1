package com.gerenciador.frota.aplicacao.integracoes.repository;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
