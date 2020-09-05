/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Optional;
import org.una.tramites.entities.TramiteCambioEstado;



/**
 *
 * @author Jeffry
 */


public interface ITramiteCambioEstadoService {
    public Optional<TramiteCambioEstado> findByUsuario(Long id);
}
