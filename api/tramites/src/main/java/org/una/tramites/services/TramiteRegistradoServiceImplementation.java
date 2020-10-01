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
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.repositories.ITramiteRegistradoRepository;

/**
 *
 * @author Pablo-VE
 */

@Service
public class TramiteRegistradoServiceImplementation implements ITramiteRegistradoService{
    
    @Autowired
    private ITramiteRegistradoRepository tramitesRegistradosRepository;
    
    @Override
    public Optional<List<TramiteRegistrado>> findAll() {
         return Optional.ofNullable(tramitesRegistradosRepository.findAll());
    }

    @Override
    public TramiteRegistrado create(TramiteRegistrado tramitesRegistrados) {
        return tramitesRegistradosRepository.save(tramitesRegistrados);
    }

    @Override
    public Optional<TramiteRegistrado> update(TramiteRegistrado tramitesRegistrados, Long id) {
        if(tramitesRegistradosRepository.findById(id).isPresent())
            return Optional.ofNullable(tramitesRegistradosRepository.save(tramitesRegistrados));
        else
            return null;
    }

    @Override
    public void delete(Long id) {
        tramitesRegistradosRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramitesRegistradosRepository.deleteAll();
    }

    @Override
    public Optional<TramiteRegistrado> findById(Long id) {
        return tramitesRegistradosRepository.findById(id);
    }
    
}
