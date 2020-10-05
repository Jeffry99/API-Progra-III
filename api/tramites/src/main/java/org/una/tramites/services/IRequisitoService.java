/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoDTO;
 

/**
 *
 * @author Jeffry
 */
public interface IRequisitoService {
    
    public Optional<List<RequisitoDTO>> findAll();

    public Optional<RequisitoDTO> findById(Long id);

    public Optional<List<RequisitoDTO>> findByVariaciones(Long Id);
    
    public RequisitoDTO create(RequisitoDTO requisito);

    public Optional<RequisitoDTO> update(RequisitoDTO requisito, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<RequisitoDTO>> findByDescripcion(String descripcion);
}
