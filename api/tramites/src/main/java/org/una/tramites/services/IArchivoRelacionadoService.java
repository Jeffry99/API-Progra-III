/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author Pablo-VE
 */
public interface IArchivoRelacionadoService {
    public Optional<ArchivoRelacionado> findById(Long id);
    
    public Optional<List<ArchivoRelacionado>> findAll();
    
    public Optional<List<ArchivoRelacionado>> findByTramiteRegistrado(Long id);
    
    public Optional<List<ArchivoRelacionado>> findByFechaRegistro(Date fechaRegistro);
    
    public ArchivoRelacionado create(ArchivoRelacionado archivoRelacionado);

    public Optional<ArchivoRelacionado> update(ArchivoRelacionado ArchivoRelacionado, Long id);

    public void delete(Long id);

    public void deleteAll();
    
}
