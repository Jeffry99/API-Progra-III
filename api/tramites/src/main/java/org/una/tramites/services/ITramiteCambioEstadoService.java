/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteCambioEstado;

/**
 *
 * @author Pablo-VE
 */
public interface ITramiteCambioEstadoService {
     public Optional<TramiteCambioEstado> findById(Long id);
    
    public Optional<List<TramiteCambioEstado>> findAll();
 
    public TramiteCambioEstado create(TramiteCambioEstado tramitesCambioE);
    
    public Optional<TramiteCambioEstado> update(TramiteCambioEstado tramitesCambioE, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
    
}
