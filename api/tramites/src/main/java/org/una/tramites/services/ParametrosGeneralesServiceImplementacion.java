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
import org.una.tramites.dto.ParametrosGeneralesDTO;
import org.una.tramites.entities.ParametrosGenerales;
import org.una.tramites.repositories.IParametrosGeneralesRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

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
    public Optional<ParametrosGeneralesDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(paramGenRepository.findById(id), ParametrosGeneralesDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGeneralesDTO>> findAll() {
        return ServiceConvertionHelper.findList(paramGenRepository.findAll(), ParametrosGeneralesDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGeneralesDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(paramGenRepository.findByEstado(estado), ParametrosGeneralesDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGeneralesDTO>> findByNombre(String nombre) {
        return ServiceConvertionHelper.findList(paramGenRepository.findByNombreContainingIgnoreCase(nombre), ParametrosGeneralesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGeneralesDTO>> findByValor(String valor) {
        return ServiceConvertionHelper.findList(paramGenRepository.findByValorContainingIgnoreCase(valor), ParametrosGeneralesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosGeneralesDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(paramGenRepository.findByDescripcionContainingIgnoreCase(descripcion), ParametrosGeneralesDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametrosGeneralesDTO> update(ParametrosGeneralesDTO parametrosG, Long id) {
        if(paramGenRepository.findById(id).isPresent()){
            ParametrosGenerales parametr = MapperUtils.EntityFromDto(parametrosG, ParametrosGenerales.class);
            parametr = paramGenRepository.save(parametr);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(parametr, ParametrosGeneralesDTO.class));
        }
        return null;
    }

    @Override
    @Transactional
    public ParametrosGeneralesDTO create(ParametrosGeneralesDTO parametrosG) { 
        ParametrosGenerales paramet = MapperUtils.EntityFromDto(parametrosG, ParametrosGenerales.class);
        paramet = paramGenRepository.save(paramet);
        return MapperUtils.DtoFromEntity(paramet, ParametrosGeneralesDTO.class);
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
    

    
}

