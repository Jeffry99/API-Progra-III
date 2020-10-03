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
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.repositories.IUsuarioRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService{

    @Autowired
    private IDepartamentoRepository departamentoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(departamentoRepository.findByEstado(estado), DepartamentoDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByNombreAproximateIgnoreCase(String nombre) {
         return ServiceConvertionHelper.findList(departamentoRepository.findByNombreContainingIgnoreCase(nombre), DepartamentoDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
         return ServiceConvertionHelper.findList(departamentoRepository.findAll(), DepartamentoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(departamentoRepository.findById(id), DepartamentoDTO.class);
    }
    
    @Override
    @Transactional
    public DepartamentoDTO create(DepartamentoDTO departamento) {
        Departamento depa = MapperUtils.EntityFromDto(departamento, Departamento.class);
        depa = departamentoRepository.save(depa);
        return MapperUtils.DtoFromEntity(depa, DepartamentoDTO.class);
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id) {
        if(departamentoRepository.findById(id).isPresent()){
            Departamento depa = MapperUtils.EntityFromDto(departamento, Departamento.class);
            depa = departamentoRepository.save(depa);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(depa, DepartamentoDTO.class));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {

        departamentoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        departamentoRepository.deleteAll();
    }
    
}
