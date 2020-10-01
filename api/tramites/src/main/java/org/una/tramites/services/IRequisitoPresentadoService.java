/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.RequisitoPresentado;

/**
 *
 * @author Pablo-VE
 */
public interface IRequisitoPresentadoService {
    public Optional<RequisitoPresentado> findById(Long id);
    
    public Optional<List<RequisitoPresentado>> findAll();
    
    public Optional<List<RequisitoPresentado>> findByTramiteRegistrado(Long id);
    
    public RequisitoPresentado create(RequisitoPresentado requisitoPresentado);
    
    public Optional<RequisitoPresentado> update(RequisitoPresentado requisitoPresentado, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
    
}
