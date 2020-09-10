/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Variacion;

/**
 *
 * @author Jeffry
 */
public interface IVariacionService {
    public Optional<List<Variacion>> findAll();

    public Optional<Variacion> findById(Long id);

    public Variacion create(Variacion variacion);

    public Optional<Variacion> update(Variacion variacion, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Variacion>> findByGrupo(String grupo);
    
    public Optional<List<Variacion>> findByDescripcion(String descripcion);
}
