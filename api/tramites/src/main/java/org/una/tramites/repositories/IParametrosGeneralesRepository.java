/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.ParametrosGenerales;

/**
 *
 * @author Luis
 */
public interface IParametrosGeneralesRepository extends JpaRepository<ParametrosGenerales, Long>{
    public List<ParametrosGenerales> findByNombreContainingIgnoreCase(String nombre);
    public List<ParametrosGenerales> findByValorContainingIgnoreCase(String valor);
    public List<ParametrosGenerales> findByDescripcionContainingIgnoreCase(String descripcion);
    public List<ParametrosGenerales> findByEstado(boolean estado);
}
