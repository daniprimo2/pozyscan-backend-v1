package com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<CargoEntity, Long> {
    @Query(value = "SELECT * FROM sc_recursos_humanos.tb_cargo \n" +
            "where lower(nome_cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))\n" +
            "AND lower(desc_cargo) LIKE LOWER(CONCAT('%', :descricaoCargo, '%'))",
            nativeQuery = true)
    List<CargoEntity> findCargoPorCargoPorDescCargo(@Param("cargo") String cargo,
                                                    @Param("descricaoCargo") String descricaoCargo);
}
