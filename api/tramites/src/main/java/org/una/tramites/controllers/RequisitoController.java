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
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.services.IRequisitoService;

/**
 *
 * @author Jeffry
 */
@RestController
@RequestMapping("/requisitos")
@Api(tags = {"Requisitos"})
public class RequisitoController {

    @Autowired
    private IRequisitoService reqService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Requisitos", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('TRA06')")
    public @ResponseBody ResponseEntity<?> findAll(){
        try {
            return new ResponseEntity<>(reqService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un requisito a traves de su identificador unico", response = RequisitoDTO.class,  tags = "Requisitos")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(reqService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/variacion/{id}")
    @ApiOperation(value = "Obtiene una lista de requisitos a traves de su variacion", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByVariacionId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(reqService.findByVariaciones(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un requisito", response = HttpStatus.class, tags = "Requisitos")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA01')")
    public ResponseEntity<?> create(@RequestBody RequisitoDTO variacion, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(reqService.create(variacion), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un requisito", response = HttpStatus.class, tags = "Requisitos")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RequisitoDTO varModified) {
        try {
            Optional<RequisitoDTO> requiUpdated = reqService.update(varModified, id);
            if (requiUpdated.isPresent()) {
                return new ResponseEntity<>(requiUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un requisito", response = HttpStatus.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            reqService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los requisitos", response = HttpStatus.class, tags = "Requisitos")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> deleteAll() {
        try {
            reqService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pordescripcion/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de requisitos por su descripcion", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try {
            return new ResponseEntity<>(reqService.findByDescripcion(descripcion), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

