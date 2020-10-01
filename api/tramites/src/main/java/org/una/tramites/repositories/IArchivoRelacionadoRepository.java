/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author Pablo-VE
 */
public interface IArchivoRelacionadoRepository extends JpaRepository<ArchivoRelacionado, Long> {
    public List<ArchivoRelacionado> findByTramitesRegistrados(Long id);
    
    public List<ArchivoRelacionado> findByFechaRegistro(Date fechaRegistro);
    
}
