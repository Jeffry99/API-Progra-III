/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;
 

/**
 *
 * @author Jeffry
 */
public interface IVariacionService {
    public Optional<List<VariacionDTO>> findAll();

    public Optional<VariacionDTO> findById(Long id);

    public VariacionDTO create(VariacionDTO variacion);
    
    public Optional<List<VariacionDTO>> findByTramitesTipos(Long Id);

    public Optional<VariacionDTO> update(VariacionDTO variacion, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<VariacionDTO>> findByGrupo(String grupo);
    
    public Optional<List<Variacion>> findByDescripcion(String descripcion);
}
