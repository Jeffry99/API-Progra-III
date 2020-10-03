/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
 

/**
 *
 * @author Pablo-VE
 */
public interface IArchivoRelacionadoService {
    public Optional<ArchivoRelacionadoDTO> findById(Long id);
    
    public Optional<List<ArchivoRelacionadoDTO>> findAll();
    
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistrado(Long id);
    
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistro(Date fechaRegistro);
    
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionado);

    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO ArchivoRelacionado, Long id);

    public void delete(Long id);

    public void deleteAll();
    
}
