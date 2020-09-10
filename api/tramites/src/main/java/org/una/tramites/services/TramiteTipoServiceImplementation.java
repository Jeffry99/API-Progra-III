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
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.repositories.ITramiteTipoRepository;

/**
 *
 * @author Jeffry
 */
@Service
public class TramiteTipoServiceImplementation implements ITramiteTipoService{

    @Autowired
    private ITramiteTipoRepository traRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipo>> findAll() {
        return Optional.ofNullable(traRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteTipo> findById(Long id) {
        return traRepository.findById(id);
    }

    @Override
    @Transactional
    public TramiteTipo create(TramiteTipo tramite) {
        return traRepository.save(tramite);
    }

    @Override
    @Transactional
    public Optional<TramiteTipo> update(TramiteTipo tramite, Long id) {
        if(traRepository.findById(id).isPresent())
            return Optional.ofNullable(traRepository.save(tramite));
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        traRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        traRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipo>> findByDepartamentoId(Long id) {
        return Optional.ofNullable(traRepository.findByDepartamentoId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipo>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(traRepository.findByDescripcion(descripcion));
    }

}

