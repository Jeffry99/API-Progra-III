/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.repositories.IArchivoRelacionadoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Pablo-VE
 */

@Service
public class ArchivoRelacionadoServiceImplementation implements IArchivoRelacionadoService{
    @Autowired
    private IArchivoRelacionadoRepository archivoRelacionadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(archivoRelacionadoRepository.findById(id), ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(archivoRelacionadoRepository.findAll(), ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistrado(Long id) {
        return ServiceConvertionHelper.findList(archivoRelacionadoRepository.findByTramitesRegistrados(id), ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistro(Date fechaRegistro) {
        return ServiceConvertionHelper.findList(archivoRelacionadoRepository.findByFechaRegistro(fechaRegistro), ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionadoDTO) {
        ArchivoRelacionado archivo = MapperUtils.EntityFromDto(archivoRelacionadoDTO, ArchivoRelacionado.class);
        archivo = archivoRelacionadoRepository.save(archivo);
        return MapperUtils.DtoFromEntity(archivo, ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO archivoRelacionadoDTO, Long id) {
        if (archivoRelacionadoRepository.findById(id).isPresent()) {
            ArchivoRelacionado archivo = MapperUtils.EntityFromDto(archivoRelacionadoDTO, ArchivoRelacionado.class);
            archivo = archivoRelacionadoRepository.save(archivo);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(archivo, ArchivoRelacionadoDTO.class));
        } else {
            return null;
        } 
    }

    @Override
    @Transactional
    public void delete(Long id) {
        archivoRelacionadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        archivoRelacionadoRepository.deleteAll();
    }
    
}
