/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Nota;

/**
 *
 * @author Pablo-VE
 */
public interface INotaService {
    public Optional<List<Nota>> findAll();
    
    public Optional<List<Nota>> findByTramitesRegistrados(long tramitesRegistrados);

    public Optional<Nota> findById(Long id);

    public Nota create(Nota variacion);

    public Optional<Nota> update(Nota notas, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<Nota> findByTitulo(String grupo);
}
