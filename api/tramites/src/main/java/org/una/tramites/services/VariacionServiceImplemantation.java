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
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.IVariacionRepository;

/**
 *
 * @author Jeffry
 */
@Service
public class VariacionServiceImplemantation implements IVariacionService{
    
    @Autowired
    private IVariacionRepository varRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variacion>> findAll() {
        return Optional.ofNullable(varRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Variacion> findById(Long id) {
        return varRepository.findById(id);
    }

    @Override
    @Transactional
    public Variacion create(Variacion variacion) {
        return varRepository.save(variacion);
    }

    @Override
    @Transactional
    public Optional<Variacion> update(Variacion variacion, Long id) {
        if(varRepository.findById(id).isPresent())
            return Optional.ofNullable(varRepository.save(variacion));
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        varRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        varRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variacion>> findByGrupo(String grupo) {
        return Optional.ofNullable(varRepository.findByGrupo(grupo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variacion>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(varRepository.findByDescripcion(descripcion));
    }

}
