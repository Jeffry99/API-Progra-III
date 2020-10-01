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
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.ITramiteTipoRepository;
import org.una.tramites.repositories.IVariacionRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Jeffry
 */
@Service
public class VariacionServiceImplemantation implements IVariacionService{
    
    @Autowired
    private IVariacionRepository varRepository;

    @Autowired
    private ITramiteTipoRepository traRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findAll() {
       return ServiceConvertionHelper.findList(varRepository.findAll(), VariacionDTO.class);
     }

    @Override
    @Transactional(readOnly = true)
    public Optional<VariacionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(varRepository.findById(id), VariacionDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findByTramitesTipos(Long id) {
        return ServiceConvertionHelper.findList(varRepository.findByTramitesTipos(id), VariacionDTO.class);
    }
    @Override
    @Transactional
    public VariacionDTO create(VariacionDTO variacion, Long id) {
        Variacion var = MapperUtils.EntityFromDto(variacion, Variacion.class);
        var.setTramitesTipos(traRepository.findById(id).get());
        var = varRepository.save(var);
        return MapperUtils.DtoFromEntity(var, VariacionDTO.class);
    }

    @Override
    @Transactional
    public Optional<VariacionDTO> update(VariacionDTO variacion, Long id) {
        if(varRepository.findById(id).isPresent()){
            Variacion var = MapperUtils.EntityFromDto(variacion, Variacion.class);
            var = varRepository.save(var);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(var, VariacionDTO.class));
        }
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
    public Optional<List<VariacionDTO>> findByGrupo(int grupo) {
        return ServiceConvertionHelper.findList(varRepository.findByGrupo(grupo), VariacionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(varRepository.findByDescripcion(descripcion), VariacionDTO.class);
    }

}
