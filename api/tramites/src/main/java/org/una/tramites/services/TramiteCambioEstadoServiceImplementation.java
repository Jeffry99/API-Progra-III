/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.repositories.ITramiteCambioEstadoRepository;



/**
 *
 * @author Jeffry
 */
@Service
public class TramiteCambioEstadoServiceImplementation implements ITramiteCambioEstadoService{
    @Autowired
    private ITramiteCambioEstadoRepository tramiteCambioEstadoRepository;
    @Override
    public Optional<TramiteCambioEstado> findByUsuario(Long id) {
        return Optional.ofNullable(tramiteCambioEstadoRepository.findByUsuario(id));
    }
    
    
}
