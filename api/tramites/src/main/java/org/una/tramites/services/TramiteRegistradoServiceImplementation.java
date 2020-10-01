/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.repositories.ITramiteRegistradoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */

@Service
public class TramiteRegistradoServiceImplementation implements ITramiteRegistradoService{
    
    @Autowired
    private ITramiteRegistradoRepository tramitesRegistradosRepository;
    
    @Override
    public Optional<List<TramiteRegistradoDTO>> findAll() {
         return ServiceConvertionHelper.findList(tramitesRegistradosRepository.findAll(), TramiteRegistradoDTO.class);
    }

    @Override
    @Transactional
    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramitesRegistrados) {
        TramiteRegistrado entidad = MapperUtils.EntityFromDto(tramitesRegistrados, TramiteRegistrado.class);
        entidad = tramitesRegistradosRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, TramiteRegistradoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramitesRegistrados, Long id) {
        if(tramitesRegistradosRepository.findById(id).isPresent()){
            TramiteRegistrado entidad = MapperUtils.EntityFromDto(tramitesRegistrados, TramiteRegistrado.class);
            entidad = tramitesRegistradosRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, TramiteRegistradoDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesRegistradosRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesRegistradosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteRegistradoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(tramitesRegistradosRepository.findById(id), TramiteRegistradoDTO.class);
    }
    
        @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findByClienteId(Long id) {
        return ServiceConvertionHelper.findList(tramitesRegistradosRepository.findByClientesIdContaining(id), TramiteRegistradoDTO.class);
    }

    @Override
    public Optional<List<TramiteRegistradoDTO>> findByTramiteTipoId(Long id) {
        return ServiceConvertionHelper.findList(tramitesRegistradosRepository.findByTramitesTiposIdContaining(id), TramiteRegistradoDTO.class);
    }

//    @Override
//    public Optional<List<TramiteRegistradoDTO>> getByFilter(String cedula, String estado, Date inicio, Date fin) {
//        return ServiceConvertionHelper.findList(tramitesRegistradosRepository.getByFilter(cedula, estado, inicio, fin), TramiteRegistradoDTO.class);
//    }
}
