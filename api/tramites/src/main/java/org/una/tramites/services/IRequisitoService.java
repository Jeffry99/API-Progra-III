/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Requisito;

/**
 *
 * @author Jeffry
 */
public interface IRequisitoService {
    
    public Optional<List<Requisito>> findAll();

    public Optional<Requisito> findById(Long id);

    public Optional<List<Requisito>> findByVariaciones(Long Id);
    
    public Requisito create(Requisito requisito);

    public Optional<Requisito> update(Requisito requisito, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Requisito>> findByDescripcion(String descripcion);
}
