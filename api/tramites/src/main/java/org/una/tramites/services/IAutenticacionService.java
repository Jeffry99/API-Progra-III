/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;

/**
 *
 * @author Luis
 */
public interface IAutenticacionService {
        public AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
