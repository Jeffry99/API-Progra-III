/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteEstado;

/**
 *
 * @author Pablo-VE
 */
public interface ITramiteEstadoService {
    public Optional<TramiteEstado> findById(Long id);

    public Optional<List<TramiteEstado>> findAll();

    public TramiteEstado create(TramiteEstado tramites);

    public Optional<TramiteEstado> update(TramiteEstado tramites, Long id);

    public void delete(Long id);

    public void deleteAll();
    
}
