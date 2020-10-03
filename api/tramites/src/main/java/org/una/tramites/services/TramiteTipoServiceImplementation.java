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
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.repositories.ITramiteTipoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

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
    public Optional<List<TramiteTipoDTO>> findAll() {
        return ServiceConvertionHelper.findList(traRepository.findAll(),TramiteTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteTipoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(traRepository.findById(id), TramiteTipoDTO.class);
    }

    @Override
    @Transactional
        public TramiteTipoDTO create(TramiteTipoDTO tramiteTipoDTO) {
        TramiteTipo entidad = MapperUtils.EntityFromDto(tramiteTipoDTO, TramiteTipo.class);
        entidad = traRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramiteTipoDTO.class);

    }

    @Override
    @Transactional
    public Optional<TramiteTipoDTO> update(TramiteTipoDTO tramiteTipoDTO, Long id) {
        if(traRepository.findById(id).isPresent()){
            TramiteTipo entidad = MapperUtils.EntityFromDto(tramiteTipoDTO, TramiteTipo.class);
            entidad = traRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramiteTipoDTO.class));
        }else{
            return null;
        }
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
    public Optional<List<TramiteTipoDTO>> findByDepartamentoId(Long id) {
        return ServiceConvertionHelper.findList(traRepository.findByDepartamentoId(id), TramiteTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findByDescripcion(String descripcion) {
        return ServiceConvertionHelper.findList(traRepository.findByDescripcionContainingIgnoreCase(descripcion), TramiteTipoDTO.class);

    }

}

