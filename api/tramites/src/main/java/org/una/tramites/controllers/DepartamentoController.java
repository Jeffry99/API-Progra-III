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
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.Usuario;
import org.una.tramites.services.IDepartamentoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/departamentos") // 
@Api(tags = {"Departamentos"})
public class DepartamentoController {
    
    @Autowired
    private IDepartamentoService departamentoService;
    
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los departamentos", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP04')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Departamento>> result = departamentoService.findAll();
            if (result.isPresent()) {
                List<DepartamentoDTO> departamentosDTO = MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un departamento a traves de su identificador unico", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Departamento> departamentoFound = departamentoService.findById(id);
            if (departamentoFound.isPresent()) {
                DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(departamentoFound.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/crear") 
    @ApiOperation(value = "Crea un departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    @ResponseBody
    @PreAuthorize("hasAuthority('DEP01')")
    public ResponseEntity<?> create(@RequestBody Departamento departamento) {
        try {
            Departamento departamentoCreated = departamentoService.create(departamento);
            DepartamentoDTO departamentoDto = MapperUtils.DtoFromEntity(departamentoCreated, DepartamentoDTO.class);
            return new ResponseEntity<>(departamentoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Exception create Departamento: "+e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}") 
    @ApiOperation(value = "Modifica un departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    @ResponseBody
    @PreAuthorize("hasAuthority('DEP02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Departamento usuarioModified) {
        try {
            Optional<Departamento> departamentoUpdated = departamentoService.update(usuarioModified, id);
            if (departamentoUpdated.isPresent()) {
                DepartamentoDTO departamentoDto = MapperUtils.DtoFromEntity(departamentoUpdated.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/estado/{estado}") 
    @ApiOperation(value = "Obtiene una lista de departamentos por estado", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP04')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Departamento>> result = departamentoService.findByEstado(estado);
            if (result.isPresent()) {
                List<DepartamentoDTO> departamentosDTO = MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/{nombre}") 
    @ApiOperation(value = "Obtiene una lista de departamentos por nombre", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP04')")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            Optional<List<Departamento>> result = departamentoService.findByNombreAproximateIgnoreCase(nombre);
            if (result.isPresent()) {
                List<DepartamentoDTO> departamentosDTO = MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);
                return new ResponseEntity<>(departamentosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @DeleteMapping("/{id}") 
    @ApiOperation(value = "Elimina un departamento", response = HttpStatus.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        //TODO: Implementar este método
        try{
                    departamentoService.delete(id);
                    if(findById(id).getStatusCode() == HttpStatus.NO_CONTENT){
                        return new ResponseEntity<>(HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }catch(Exception ex){
                    return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
                }
    }

    @DeleteMapping("/") 
    @ApiOperation(value = "Elimina todos los departamentos", response = HttpStatus.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEP03')")
    public ResponseEntity<?> deleteAll() {
 	//TODO: Implementar este método
        try{
            departamentoService.deleteAll();
            if(findAll().getStatusCode() == HttpStatus.NO_CONTENT){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
}
