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
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Jeffry
 */
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    @Autowired
    private IRequisitoRepository reqRepo;
    
    @Override
    public Optional<List<RequisitoDTO>> findAll() {
        return ServiceConvertionHelper.findList(reqRepo.findAll(), RequisitoDTO.class);
    }

    @Override
    public Optional<RequisitoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(reqRepo.findById(id), RequisitoDTO.class);
    }
    
    @Override
    public Optional<List<RequisitoDTO>> findByVariaciones(Long variacionId){
        return ServiceConvertionHelper.findList(reqRepo.findByVariaciones(variacionId), RequisitoDTO.class);
    }

    @Override
    public RequisitoDTO create(RequisitoDTO requisito) {
       Requisito requis = MapperUtils.EntityFromDto(requisito, Requisito.class);
        requis = reqRepo.save(requis);
        return MapperUtils.DtoFromEntity(requis, RequisitoDTO.class);
    }

    @Override
    public Optional<RequisitoDTO> update(RequisitoDTO requisito, Long id) {
        if(reqRepo.findById(id).isPresent()){
            Requisito requis = MapperUtils.EntityFromDto(requisito, Requisito.class);
            requis = reqRepo.save(requis);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(requis, RequisitoDTO.class));
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
    public Optional<List<RequisitoDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(reqRepo.findByDescripcion(descripcion), RequisitoDTO.class);
    }
}

