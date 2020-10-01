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
import org.una.tramites.entities.ParametrosGenerales;
import org.una.tramites.repositories.IParametrosGeneralesRepository;

/**
 *
 * @author Luis
 */
@Service
public class ParametrosGeneralesServiceImplementacion implements IParametrosGeneralesService{

    @Autowired
    private IParametrosGeneralesRepository paramGenRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findByEstado(boolean estado) {
        return Optional.ofNullable(paramGenRepository.findByEstado(estado));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findByNombre(String nombre) {
        return Optional.ofNullable(paramGenRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findByValor(String valor) {
        return Optional.ofNullable(paramGenRepository.findByValorContainingIgnoreCase(valor));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(paramGenRepository.findByDescripcionContainingIgnoreCase(descripcion));
    }

    @Override
    @Transactional
    public Optional<ParametrosGenerales> update(ParametrosGenerales pg, Long id) {
        if(paramGenRepository.findById(id).isPresent()){
            return Optional.ofNullable(paramGenRepository.save(pg));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGenerales>> findAll() {
        return Optional.ofNullable(paramGenRepository.findAll());
    }

    @Override
    @Transactional
    public ParametrosGenerales create(ParametrosGenerales pg) {
        return paramGenRepository.save(pg);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        paramGenRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        paramGenRepository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosGenerales> findById(Long id) {
        return paramGenRepository.findById(id);
    }
    
}

