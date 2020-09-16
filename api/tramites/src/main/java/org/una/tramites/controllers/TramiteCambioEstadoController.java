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
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.services.ITramiteCambioEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/tramites_cambio_estado")
@Api(tags = {"Tramites_Cambio_Estado"})
public class TramiteCambioEstadoController {

    @Autowired
    private ITramiteCambioEstadoService tramiteService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites cambio estado", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites_Cambio_Estado")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteCambioEstado>> result = tramiteService.findAll();
            if (result.isPresent()) {
                List<TramiteCambioEstadoDTO> tramitesDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramitesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite cambio estado a traves de su identificador unico", response = TramiteCambioEstadoDTO.class, tags = "Tramites_Cambio_Estado")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteCambioEstado> tramitesFound = tramiteService.findById(id);
            if (tramitesFound.isPresent()) {
                TramiteCambioEstadoDTO tramitesDTO = MapperUtils.DtoFromEntity(tramitesFound.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramitesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un tramite cambio estado", response = HttpStatus.class, tags = "Tramites_Cambio_Estado")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TramiteCambioEstado tramites) {
        try {
            TramiteCambioEstado tramitesCreated = tramiteService.create(tramites);
            TramiteCambioEstadoDTO tramitesDto = MapperUtils.DtoFromEntity(tramitesCreated, TramiteCambioEstadoDTO.class);
            return new ResponseEntity<>(tramitesDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un tramite cambio estado", response = HttpStatus.class, tags = "Tramites_Cambio_Estado")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteCambioEstado tramitesModified) {
        try {
            Optional <TramiteCambioEstado> tramitesUpdated = tramiteService.update(tramitesModified, id);
            if (tramitesUpdated.isPresent()) {
                TramiteCambioEstadoDTO tramitesDto = MapperUtils.DtoFromEntity(tramitesUpdated.get(), TramiteCambioEstadoDTO.class);
                return new ResponseEntity<>(tramitesDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un tramite cambio estado", response = HttpStatus.class, tags = "Tramites_Cambio_Estado")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los tramites cambio estados", response = HttpStatus.class, tags = "Tramites_Cambio_Estado")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}