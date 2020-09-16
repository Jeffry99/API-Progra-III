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
 
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Requisitos", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    public @ResponseBody ResponseEntity<?> findAll(){
        try{
            Optional<List<Requisito>> result = reqService.findAll();
            if(result.isPresent()){
                List<RequisitoDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un requisito a traves de su identificador unico", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Requisito> variacionFound = reqService.findById(id);
            if (variacionFound.isPresent()) {
                RequisitoDTO variacionDto = MapperUtils.DtoFromEntity(variacionFound.get(), RequisitoDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un requisito", response = HttpStatus.class, tags = "Requisitos")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Requisito variacion) {
        try {
            Requisito varCreated = reqService.create(variacion);
            RequisitoDTO varDto = MapperUtils.DtoFromEntity(varCreated, RequisitoDTO.class);
            return new ResponseEntity<>(varDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un requisito", response = HttpStatus.class, tags = "Requisitos")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Requisito varModified) {
        try {
            Optional<Requisito> varUpdated = reqService.update(varModified, id);
            if (varUpdated.isPresent()) {
                RequisitoDTO usuarioDto = MapperUtils.DtoFromEntity(varUpdated.get(), RequisitoDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un requisito", response = HttpStatus.class, tags = "Requisitos")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            reqService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los requisitos", response = HttpStatus.class, tags = "Requisitos")
    public ResponseEntity<?> deleteAll() {
        try {
            reqService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de requisitos por su descripcion", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try{
            Optional<List<Requisito>> result = reqService.findByDescripcion(descripcion);
            if(result.isPresent()){
                List<RequisitoDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

