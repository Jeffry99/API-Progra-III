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
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.repositories.INotaRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */

@Service
public class NotaServiceImplementation implements INotaService{
    @Autowired
    private INotaRepository notasRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findAll() {
        return ServiceConvertionHelper.findList(notasRepository.findAll(),NotaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(notasRepository.findById(id),NotaDTO.class);
    }

    @Override
    @Transactional
    public NotaDTO create(NotaDTO notas) {
        Nota archiv = MapperUtils.EntityFromDto(notas, Nota.class);
        archiv = notasRepository.save(archiv);
        return MapperUtils.DtoFromEntity(archiv, NotaDTO.class);
    }

    @Override
    @Transactional
     public Optional<NotaDTO> update(NotaDTO notas, Long id) {
        if (notasRepository.findById(id).isPresent()) {
            Nota archiv = MapperUtils.EntityFromDto(notas, Nota.class);
            archiv = notasRepository.save(archiv);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(archiv, NotaDTO.class));
        } else {
            return null;
        } 
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notasRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        notasRepository.deleteAll();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<NotaDTO>> findByTitulo(String cedula) {
//        return ServiceConvertionHelper.findList(notasRepository.findByTitulo(cedula), NotaDTO.class);  
//   }
 }
