/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.Cliente;

/**
 *
 * @author Pablo-VE
 */
public interface IClienteRepository extends JpaRepository<Cliente, Long>{
    
    public Cliente findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);

    public List<Cliente> findByCedulaContaining(String cedula);

    public List<Cliente> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);
    
    public List<Cliente> findByCedula(String cedula);
    
}
