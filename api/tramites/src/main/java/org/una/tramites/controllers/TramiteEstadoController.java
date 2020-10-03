/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.services.ITramiteEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */

@RestController
@RequestMapping("/tramites_estados")
@Api(tags = {"Tramites_Estados"})
public class TramiteEstadoController {
    
    @Autowired
    private ITramiteEstadoService tramiteEstadoService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites estados", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TRA06')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tramiteEstadoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene tramite estado a traves de su identificador unico", response = TramiteEstadoDTO.class, tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramiteEstadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un tramite estado", response = HttpStatus.class, tags = "Tramites_Estados")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA01')")
    public ResponseEntity<?> create(@RequestBody TramiteEstadoDTO tramite, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tramiteEstadoService.create(tramite), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un tramite estado", response = HttpStatus.class, tags = "Tramites_Estados")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteEstadoDTO traModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TramiteEstadoDTO> usuarioUpdated = tramiteEstadoService.update(traModified, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un tramite estado", response = HttpStatus.class, tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteEstadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los tramites estado", response = HttpStatus.class, tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteEstadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
