/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author Pablo-VE
 */
public interface ITramiteRegistradoRepository extends JpaRepository<TramiteRegistrado, Long> {
  //  public List<TramiteRegistrado> findByClientesId(Long id);
    
 //   public List<TramiteRegistrado> findByTramitesTiposId(Long id);
    
}
