/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ParametrosGeneralesDTO;


/**
 *
 * @author Luis
 */
public interface IParametrosGeneralesService {
    public Optional<List<ParametrosGeneralesDTO>> findByNombre(String nombre);
    
    public Optional<List<ParametrosGeneralesDTO>> findByDescripcion(String descripcion);
    
    public Optional<List<ParametrosGeneralesDTO>> findByValor(String valor);
    
    public Optional<List<ParametrosGeneralesDTO>> findByEstado(boolean estado);
    
    public Optional<ParametrosGeneralesDTO> update(ParametrosGeneralesDTO pg, Long id);
    
    public Optional<List<ParametrosGeneralesDTO>> findAll();
    
    public void delete(Long id);
    
    public void deleteAll();
    
    public ParametrosGeneralesDTO create(ParametrosGeneralesDTO pg);
    
    public Optional<ParametrosGeneralesDTO> findById(Long id);
    
}
