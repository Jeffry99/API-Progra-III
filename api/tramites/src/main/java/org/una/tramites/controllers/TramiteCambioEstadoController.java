/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.RequisitosDTO;
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.Requisitos;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.services.ITramiteCambioEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Jeffry
 */
@RestController
@RequestMapping("/tramitesCambioEstado")
@Api(tags = {"TramitesCambioEstado"})
public class TramiteCambioEstadoController {
    @Autowired
    private ITramiteCambioEstadoService tramiteCambioService;
    
    @GetMapping("/usuario/{term}") 
    @ApiOperation(value = "Obtiene un tramite cambio estado por usuario", response = TramiteCambioEstado.class, tags = "TramitesCambioEstado")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "term") Long term)
{
            try {

                Optional<TramiteCambioEstado> TramiteCambioEstadoFound = tramiteCambioService.findByUsuario(term);
                if (TramiteCambioEstadoFound.isPresent()) {
                    TramiteCambioEstadoDTO TramiteCambioEstadoDto = MapperUtils.DtoFromEntity(TramiteCambioEstadoFound.get(), TramiteCambioEstadoDTO.class);
                    return new ResponseEntity<>(TramiteCambioEstadoDto, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } catch (Exception ex) {
                return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
}
