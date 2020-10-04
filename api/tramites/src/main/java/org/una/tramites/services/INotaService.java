/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
 

/**
 *
 * @author Pablo-VE
 */
public interface INotaService {
    public Optional<List<NotaDTO>> findAll();
    
    public Optional<List<NotaDTO>> findByTramitesRegistrados(long tramitesRegistrados);

    public Optional<NotaDTO> findById(Long id);

    public NotaDTO create(NotaDTO nota);

    public Optional<NotaDTO> update(NotaDTO notas, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<Nota> findByTitulo(String grupo);
}
