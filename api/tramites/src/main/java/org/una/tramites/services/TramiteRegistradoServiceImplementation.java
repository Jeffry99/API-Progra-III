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
    public Optional<TramiteRegistradoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(tramitesRegistradosRepository.findById(id), TramiteRegistradoDTO.class);
    }

    @Override
    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramitesRegistrados) {
        TramiteRegistrado tramiteReg = MapperUtils.EntityFromDto(tramitesRegistrados, TramiteRegistrado.class);
        tramiteReg = tramitesRegistradosRepository.save(tramiteReg);
        return MapperUtils.DtoFromEntity(tramiteReg, TramiteRegistradoDTO.class);
    }

    @Override
    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramitesRegistrados, Long id) {
        if(tramitesRegistradosRepository.findById(id).isPresent()){
            TramiteRegistrado per = MapperUtils.EntityFromDto(tramitesRegistrados, TramiteRegistrado.class);
            per = tramitesRegistradosRepository.save(per);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(per, TramiteRegistradoDTO.class));
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        tramitesRegistradosRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramitesRegistradosRepository.deleteAll();
    }
    
}
