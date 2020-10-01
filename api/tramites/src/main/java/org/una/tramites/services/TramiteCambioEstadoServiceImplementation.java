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
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.repositories.ITramiteCambioEstadoRepository;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TramiteCambioEstadoServiceImplementation implements ITramiteCambioEstadoService {
    
    @Autowired
    private ITramiteCambioEstadoRepository tramitesCambioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteCambioEstado> findById(Long id) {
        return tramitesCambioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteCambioEstado>> findAll() {
        return Optional.ofNullable(tramitesCambioRepository.findAll());
    }

    @Override
    @Transactional
    public TramiteCambioEstado create(TramiteCambioEstado tramitesCambioE) {
        return tramitesCambioRepository.save(tramitesCambioE);
    }

    @Override
    @Transactional
    public Optional<TramiteCambioEstado> update(TramiteCambioEstado tramitesCambioE, Long id) {
        if (tramitesCambioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramitesCambioRepository.save(tramitesCambioE));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesCambioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesCambioRepository.deleteAll();
    }
    
}
