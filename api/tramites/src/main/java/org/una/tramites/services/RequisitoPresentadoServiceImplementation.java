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
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.repositories.IRequisitoPresentadoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */

@Service
public class RequisitoPresentadoServiceImplementation implements IRequisitoPresentadoService{
    @Autowired
    private IRequisitoPresentadoRepository requisitoPresentadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoPresentadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(requisitoPresentadoRepository.findById(id), RequisitoPresentadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(requisitoPresentadoRepository.findAll(), RequisitoPresentadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentadoDTO>> findByTramiteRegistrado(Long id) {
        return ServiceConvertionHelper.findList(requisitoPresentadoRepository.findByTramitesRegistrados(id), RequisitoPresentadoDTO.class);
    }

    @Override
    @Transactional
    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentado) {
        RequisitoPresentado req = MapperUtils.EntityFromDto(requisitoPresentado, RequisitoPresentado.class);
        req = requisitoPresentadoRepository.save(req);
        return MapperUtils.DtoFromEntity(req, RequisitoPresentadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentado, Long id) {
        if(requisitoPresentadoRepository.findById(id).isPresent()){
            RequisitoPresentado req = MapperUtils.EntityFromDto(requisitoPresentado, RequisitoPresentado.class);
            req = requisitoPresentadoRepository.save(req);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(req, RequisitoPresentadoDTO.class));
        }else
            return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        requisitoPresentadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        requisitoPresentadoRepository.deleteAll();
    }
}
