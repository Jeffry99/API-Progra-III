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
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.services.ITramiteTipoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Jeffry
 */
@RestController
@RequestMapping("/tramites_tipos")
@Api(tags = {"Tramites_Tipos"})
public class TramiteTipoController {

    @Autowired
    private ITramiteTipoService traService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de tramites", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRA06')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(traService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un usuario por su id", response = TramiteTipoDTO.class, tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(traService.findById(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un tipo de tramite", response = HttpStatus.class, tags = "Tramites_Tipos")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA01')")
    public ResponseEntity<?> create(@RequestBody TramiteTipoDTO tramites, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(traService.create(tramites), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un tipo de tramite", response = HttpStatus.class, tags = "Tramites_Tipos")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteTipoDTO traModified, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            try{
                Optional<TramiteTipoDTO> updated = traService.update(traModified, id);
                if(updated.isPresent()){
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                }catch(Exception ex){
                    return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }else{
            return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un tipo de tramite", response = HttpStatus.class, tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            traService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los tipos de tramites", response = HttpStatus.class, tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> deleteAll() {
        try {
            traService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/usuarios_en_departamento/{depaId}")
    @ApiOperation(value = "Obtiene una lista de tipos de tramites por su departamento", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "depaId") Long depaId) {
        try {
            return new ResponseEntity<>(traService.findByDepartamentoId(depaId), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/buscarDescripcion/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de tipos de tramites por su descripcion", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion") String descripcion) {
        try {
            return new ResponseEntity<>(traService.findByDescripcion(descripcion), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

