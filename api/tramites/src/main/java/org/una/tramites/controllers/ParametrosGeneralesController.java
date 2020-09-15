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
import org.una.tramites.dto.ParametrosGeneralesDTO;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.ParametrosGenerales;
import org.una.tramites.entities.Permiso;
import org.una.tramites.services.IParametrosGeneralesService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Luis
 */
@RestController
@RequestMapping("/parametros_generales")
@Api(tags = {"Parametros_Generales"})
public class ParametrosGeneralesController {
    
    @Autowired
    private IParametrosGeneralesService paramGenService;
    
    @GetMapping("/pornombre/{nombre}")
    @ApiOperation(value = "Obtiene los Paremetros Generales segun el nombre", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre")String nombre) {
        try{
            Optional<List<ParametrosGenerales>> result = paramGenService.findByNombre(nombre);
            if(result.isPresent()){
                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un parametro general por su identificador unico", response = ParametrosGeneralesDTO.class, tags = "Parametros_Generales")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<ParametrosGenerales> permisoFound = paramGenService.findById(id);
            if (permisoFound.isPresent()) {
                PermisoDTO perDto = MapperUtils.DtoFromEntity(permisoFound.get(), PermisoDTO.class);
                return new ResponseEntity<>(perDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/porvalor/{valor}")
    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun el valor que guardan", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor){
        try{
            Optional<List<ParametrosGenerales>> result = paramGenService.findByValor(valor);
            if(result.isPresent()){
                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/pordescripcion/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun su descripcion", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try{
            Optional<List<ParametrosGenerales>> result = paramGenService.findByDescripcion(descripcion);
            if(result.isPresent()){
                List<ParametrosGeneralesDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un parametro general", response = HttpStatus.class, tags = "Parametros_Generales")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametrosGenerales parGen) {
        try {
            Optional<ParametrosGenerales> parGenUpdate = paramGenService.update(parGen, id);
            if (parGenUpdate.isPresent()) {
                ParametrosGeneralesDTO parGenDto = MapperUtils.DtoFromEntity(parGenUpdate.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(parGenDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Parametros Generales", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametrosGenerales>> result = paramGenService.findAll();
            if (result.isPresent()) {
                List<ParametrosGeneralesDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/porestado/{estado}") 
    @ApiOperation(value = "Obtiene una lista de parametros generales por estado", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<ParametrosGenerales>> result = paramGenService.findByEstado(estado);
            if (result.isPresent()) {
                List<ParametrosGeneralesDTO> permisoDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametrosGeneralesDTO.class);
                return new ResponseEntity<>(permisoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un parametro general", response = HttpStatus.class, tags = "Parametros_Generales")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ParametrosGenerales pg) {
        try {
            ParametrosGenerales pgCreated = paramGenService.create(pg);
            PermisoDTO perDto = MapperUtils.DtoFromEntity(pgCreated, PermisoDTO.class);
            return new ResponseEntity<>(perDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un parametro general", response = HttpStatus.class, tags = "Parametros_Generales")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            paramGenService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los permisos", response = HttpStatus.class, tags = "Parametros_Generales")
    public ResponseEntity<?> deleteAll() {
        try {
            paramGenService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

