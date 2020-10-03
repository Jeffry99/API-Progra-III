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
import org.una.tramites.entities.Nota;

/**
 *
 * @author Pablo-VE
 */
public interface INotaRepository extends JpaRepository<Nota, Long> {
    public Nota findByTitulo(String titulo);
    
    @Query("SELECT n FROM Nota n LEFT JOIN n.tramitesRegistrados t WHERE t.id = :tramiteID")
    public List<Nota> findByTramitesRegistrados(@Param("tramiteID")long tramitesRegistrados);
}
