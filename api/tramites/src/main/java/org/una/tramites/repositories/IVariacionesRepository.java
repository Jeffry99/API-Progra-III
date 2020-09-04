/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Variaciones;

/**
 *
 * @author Jeffry
 */
public interface IVariacionesRepository extends JpaRepository<Variaciones, Long>{
    
    public List<Variaciones> findByGrupo(String grupo);
    
    public List<Variaciones> findByDescripcion(String descripcion);
}
