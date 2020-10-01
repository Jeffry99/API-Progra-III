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
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.repositories.ITramiteCambioEstadoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class TramiteCambioEstadoServiceImplementation implements ITramiteCambioEstadoService {
    
    @Autowired
    private ITramiteCambioEstadoRepository tramitesCambioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteCambioEstadoDTO> findById(Long id) {
       return ServiceConvertionHelper.oneToOptionalDto(tramitesCambioRepository.findById(id), TramiteCambioEstadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteCambioEstadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(tramitesCambioRepository.findAll(), TramiteCambioEstadoDTO.class);
    }

    @Override
    @Transactional
    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO tramitesCambioE) {
          TramiteCambioEstado entidad = MapperUtils.EntityFromDto(tramitesCambioE, TramiteCambioEstado.class);
        entidad = tramitesCambioRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramiteCambioEstadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramitesCambioE, Long id) {
         if(tramitesCambioRepository.findById(id).isPresent()){
            TramiteCambioEstado entidad = MapperUtils.EntityFromDto(tramitesCambioE, TramiteCambioEstado.class);
            entidad = tramitesCambioRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramiteCambioEstadoDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesCambioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesCambioRepository.deleteAll();
    }
    
    
}
