/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.TramiteCambioEstado;

/**
 *
 * @author Pablo-VE
 */
public interface ITramiteCambioEstadoRepository extends JpaRepository<TramiteCambioEstado, Long>{
    
    @Query("SELECT n FROM TramiteCambioEstado n LEFT JOIN n.tramitesRegistrados t WHERE t.id = :tramiteID")
    public List<TramiteCambioEstado> findByTramitesRegistrados(@Param("tramiteID")long tramitesRegistrados);
    
}
