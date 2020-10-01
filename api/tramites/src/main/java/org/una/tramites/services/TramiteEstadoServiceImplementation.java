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
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteEstadoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */

@Service
public class TramiteEstadoServiceImplementation implements ITramiteEstadoService{
    @Autowired
    private ITramiteEstadoRepository tramitesEstadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteEstadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(tramitesEstadoRepository.findById(id), TramiteEstadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteEstadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(tramitesEstadoRepository.findAll(), TramiteEstadoDTO.class);
    }

    @Override
    @Transactional
    public TramiteEstadoDTO create(TramiteEstadoDTO tramites) {
        TramiteEstado entidad = MapperUtils.EntityFromDto(tramites, TramiteEstado.class);
        entidad = tramitesEstadoRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramiteEstadoDTO.class);
    }

    @Override
    @Transactional
     public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramites, Long id) {
        if(tramitesEstadoRepository.findById(id).isPresent()){
            TramiteEstado entidad = MapperUtils.EntityFromDto(tramites, TramiteEstado.class);
            entidad = tramitesEstadoRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramiteEstadoDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesEstadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesEstadoRepository.deleteAll();
    }
    
}
