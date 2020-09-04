/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author Luis
 */
public interface IPermisoRepository extends JpaRepository<Permiso, Long>{
    
    public List<Permiso> findByCodigoContaining(String codigo);
    
    public List<Permiso> findByFechaRegistro(Date fechaRegistro);
    
    public List<Permiso> findByFechaModificacion(Date fechaModificacion);
    
    @Query("select p from Permiso p where p.codigo = :codigo")
    public Permiso findByCodigo(@PathParam("codigo")String codigo);
}
