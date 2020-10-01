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
import javax.validation.Valid;
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
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/pornombre/{nombre}")
    @ApiOperation(value = "Obtiene los paremetros generales segun el nombre", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre")String nombre) {
        try {
            return new ResponseEntity<>(paramGenService.findByNombre(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un parametro general por su identificador unico", response = ParametrosGeneralesDTO.class, tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(paramGenService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/porvalor/{valor}")
    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun el valor que guardan", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor){
        try {
            return new ResponseEntity<>(paramGenService.findByValor(valor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/pordescripcion/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de Parametros Generales segun su descripcion", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try {
            return new ResponseEntity<>(paramGenService.findByDescripcion(descripcion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un parametro general", response = HttpStatus.class, tags = "Parametros_Generales")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ParametrosGeneralesDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ParametrosGeneralesDTO> Updated = paramGenService.update(usuarioDTO, id);
                if (Updated.isPresent()) {
                    return new ResponseEntity(Updated, HttpStatus.OK);
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
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los parametros generales", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU04')")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(paramGenService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/porestado/{estado}") 
    @ApiOperation(value = "Obtiene una lista de parametros generales por estado", response = ParametrosGeneralesDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(paramGenService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un parametro general", response = HttpStatus.class, tags = "Parametros_Generales")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU01')")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ParametrosGeneralesDTO usuario) {
        try {
            return new ResponseEntity(paramGenService.create(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un parametro general", response = HttpStatus.class, tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
          try {
            paramGenService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los permisos", response = HttpStatus.class, tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('USU03')")
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

