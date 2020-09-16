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
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;

/**
 *
 * @author Jeffry
 */
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    @Autowired
    private IRequisitoRepository reqRepo;
    
    @Override
    public Optional<List<Requisito>> findAll() {
        return Optional.ofNullable(reqRepo.findAll());
    }

    @Override
    public Optional<Requisito> findById(Long id) {
        return reqRepo.findById(id);
    }
    
    @Override
    public Optional<List<Requisito>> findByVariaciones(Long variacionId){
        return Optional.ofNullable(reqRepo.findByVariaciones(variacionId));
    }

    @Override
    public Requisito create(Requisito requisito) {
        return reqRepo.save(requisito);
    }

    @Override
    public Optional<Requisito> update(Requisito requisito, Long id) {
        if(reqRepo.findById(id).isPresent()){
            return Optional.ofNullable(reqRepo.save(requisito));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        reqRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reqRepo.deleteAll();
    }

    @Override
    public Optional<List<Requisito>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(reqRepo.findByDescripcion(descripcion));
    }

}

