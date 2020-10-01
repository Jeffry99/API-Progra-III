/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteCambioEstadoDTO;


/**
 *
 * @author Pablo-VE
 */
public interface ITramiteCambioEstadoService {
     public Optional<TramiteCambioEstadoDTO> findById(Long id);
    
    public Optional<List<TramiteCambioEstadoDTO>> findAll();
 
    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO tramitesCambioE);
    
    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramitesCambioE, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
    
}
