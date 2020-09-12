/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteEstadoRepository;

/**
 *
 * @author Pablo-VE
 */

@Service
public class TramiteEstadoServiceImplementation implements ITramiteEstadoService{
    @Autowired
    private ITramiteEstadoRepository tramitesEstadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteEstado> findById(Long id) {
        return tramitesEstadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteEstado>> findAll() {
        return Optional.ofNullable(tramitesEstadoRepository.findAll());
    }

    @Override
    @Transactional
    public TramiteEstado create(TramiteEstado tramitesE) {
        return tramitesEstadoRepository.save(tramitesE);
    }

    @Override
    @Transactional
    public Optional<TramiteEstado> update(TramiteEstado tramitesE, Long id) {
        if (tramitesEstadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramitesEstadoRepository.save(tramitesE));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesEstadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesEstadoRepository.deleteAll();
    }
    
}
