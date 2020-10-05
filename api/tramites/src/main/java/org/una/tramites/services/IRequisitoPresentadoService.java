/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoPresentadoDTO;
 

/**
 *
 * @author Pablo-VE
 */
public interface IRequisitoPresentadoService {
    public Optional<RequisitoPresentadoDTO> findById(Long id);
    
    public Optional<List<RequisitoPresentadoDTO>> findAll();
    
    public Optional<List<RequisitoPresentadoDTO>> findByTramiteRegistrado(Long id);
    
    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentado);
    
    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentado, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
    
}
