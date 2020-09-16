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
import org.una.tramites.entities.Requisito;

/**
 *
 * @author Jeffry
 */
public interface IRequisitoRepository extends JpaRepository<Requisito, Long>{
    
    public List<Requisito> findByDescripcion(String descripcion);
    
   // @Query("SELECT * FROM Requisito r LEFT JOIN r.variaciones v WHERE v.id = :variacionID")
    public List<Requisito> findByVariaciones(/*@Param("variacionID") */Long id);
}
