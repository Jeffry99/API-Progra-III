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
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author Pablo-VE
 */
public interface ITramiteRegistradoRepository extends JpaRepository<TramiteRegistrado, Long> {
    @Query("SELECT t FROM TramiteRegistrado t LEFT JOIN t.cliente c WHERE UPPER(c.cedula) like  CONCAT('%', UPPER(:cedulaC), '%')")
    public List<TramiteRegistrado> findByCedulaCliente(@Param("cedulaC") String cedulaC);
    
 //   public List<TramiteRegistrado> findByTramitesTiposId(Long id);
    
}
