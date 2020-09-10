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
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.services.ITramiteRegistradoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/tramites_registrados")
@Api(tags = {"Tramites_Registrados"})
public class TramiteRegistradoController {

    @Autowired
    private ITramiteRegistradoService tramitesRegistradosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteRegistrado>> result = tramitesRegistradosService.findAll();
            if (result.isPresent()) {
                List<TramiteRegistradoDTO> tramitesDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramitesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite registrado a traves de su identificador unico", response = TramiteRegistradoDTO.class, tags = "Tramites_Registrados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteRegistrado> tramitesFound = tramitesRegistradosService.findById(id);
            if (tramitesFound.isPresent()) {
                TramiteRegistradoDTO tramitesDTO = MapperUtils.DtoFromEntity(tramitesFound.get(), TramiteRegistradoDTO.class);
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
    @ApiOperation(value = "Crea un tramite registrado", response = HttpStatus.class, tags = "Tramites_Registrados")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody TramiteRegistrado tramites) {
        try {
            TramiteRegistrado tramitesCreated = tramitesRegistradosService.create(tramites);
            TramiteRegistradoDTO tramitesDto = MapperUtils.DtoFromEntity(tramitesCreated, TramiteRegistradoDTO.class);
            return new ResponseEntity<>(tramitesDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un tramite registrado", response = HttpStatus.class, tags = "Tramites_Registrados")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteRegistrado tramitesModified) {
        try {
            Optional<TramiteRegistrado> tramitesUpdated = tramitesRegistradosService.update(tramitesModified, id);
            if (tramitesUpdated.isPresent()) {
                TramiteRegistradoDTO tramitesDto = MapperUtils.DtoFromEntity(tramitesUpdated.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramitesDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un tramite registrado", response = HttpStatus.class, tags = "Tramites_Registrados")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramitesRegistradosService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los tramites registrados", response = HttpStatus.class, tags = "Tramites_Registrados")
    public ResponseEntity<?> deleteAll() {
        try {
            tramitesRegistradosService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

