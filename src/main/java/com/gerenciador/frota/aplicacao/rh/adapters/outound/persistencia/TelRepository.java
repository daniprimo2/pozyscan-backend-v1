package com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelRepository extends JpaRepository<TelefonesEntity, Long> {

    @Query(value = "select * from sc_recursos_humanos.tb_telefone tl where tl.cod_colaborador = :codigoColaborador", nativeQuery = true)
    List<TelefonesEntity> findBycolaboradorEntity(@Param("codigoColaborador") Long codigoColaborador);

    @Query(value = "select * from sc_recursos_humanos.tb_telefone where cod_telefone = :codigoTelefone AND tipo_contato = CAST(:tipoContato AS VARCHAR)", nativeQuery = true)
    TelefonesEntity findByCodigoTelefoneAndTipoContato(@Param("codigoTelefone") Long codigoTelefone, @Param("tipoContato") String tipoContato);
}
