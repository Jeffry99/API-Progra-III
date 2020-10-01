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
import org.una.tramites.repositories.IVariacionRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Jeffry
 */
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    @Autowired
    private IRequisitoRepository requisitoRepos;
    
    @Autowired
    private IVariacionRepository variacionRepos;
    
    @Override
    public Optional<List<RequisitoDTO>> findAll() {
        return ServiceConvertionHelper.findList(requisitoRepos.findAll(), RequisitoDTO.class);
    }

    @Override
    public Optional<RequisitoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(requisitoRepos.findById(id), RequisitoDTO.class);
    }
    
    @Override
    public Optional<List<RequisitoDTO>> findByVariaciones(Long variacionId){
        return ServiceConvertionHelper.findList(requisitoRepos.findByVariaciones(variacionId), RequisitoDTO.class);
    }
    @Override
    public RequisitoDTO create(RequisitoDTO requisito, Long id) {
        Requisito req = MapperUtils.EntityFromDto(requisito, Requisito.class);
        req.setVariaciones(variacionRepos.findById(id).get());
        req = requisitoRepos.save(req);
        return MapperUtils.DtoFromEntity(req, RequisitoDTO.class);
    }
    
    @Override
    public Optional<RequisitoDTO> update(RequisitoDTO requisito, Long id) {
        if(requisitoRepos.findById(id).isPresent()){
            Requisito req = MapperUtils.EntityFromDto(requisito, Requisito.class);
            req = requisitoRepos.save(req);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(req, RequisitoDTO.class));
        }
        return null;
    }

     @Override
    public void delete(Long id) {
        requisitoRepos.deleteById(id);
    }

    @Override
    public void deleteAll() {
        requisitoRepos.deleteAll();
    }

    @Override
    public Optional<List<RequisitoDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(requisitoRepos.findByDescripcion(descripcion), RequisitoDTO.class);
    }


}

