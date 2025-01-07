package com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailEmtity, Long> {

    @Query(value = "select * from sc_recursos_humanos.tb_email em where em.cod_colaborador = :codigoColaborador", nativeQuery = true)
    List<EmailEmtity> findBycolaboradorEntity(Long codigoColaborador);

    @Query(value = "select * from sc_recursos_humanos.tb_email where cod_email = :codigoEmail AND tipo_contato = CAST(:tipoContato AS VARCHAR)", nativeQuery = true)
    EmailEmtity findByCodigoEmailAndTipoContato(Long codigoEmail, String tipoContato);
}
