package com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DocumentosRepository extends JpaRepository<DocumentosEntity, Long> {
    @Transactional
    @Query(value = "select * from sc_recursos_humanos.tb_documentos dc where dc.cod_colaborador = :codigoColaborador", nativeQuery = true)
    List<DocumentosEntity> findBycolaboradorEntity(Long codigoColaborador);

    @Query(value = "select * from sc_recursos_humanos.tb_documentos where tipo_documento = :tipoDocumento AND cod_documento = :codigoDocumento", nativeQuery = true)
    DocumentosEntity findByTipoDocumento(@Param("codigoDocumento") Long codigoColaborador, @Param("tipoDocumento") TipoDocumento tipoDocumento);

}
