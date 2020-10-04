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
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.services.NotaServiceImplementation;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Pablo-VE
 */

@RestController
@RequestMapping("/notas")
@Api(tags = {"Notas"})
public class NotaController {
     @Autowired
    private NotaServiceImplementation notasService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos las notas", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('USU04')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(notasService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tramitesRegistrados/{term}")
    @ApiOperation(value = "Obtiene una lista de todos las notas por tramite registrado", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('USU04')")
    public @ResponseBody
    ResponseEntity<?> findByTramiteRegistrado(@PathVariable(value = "term") Long term) {
        try {
            return new ResponseEntity<>(notasService.findByTramitesRegistrados(term), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una notas a traves de su identificador unico", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(notasService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea una nota", response = HttpStatus.class, tags = "Notas")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU01')")
    public ResponseEntity<?> create(@RequestBody NotaDTO notas, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(notasService.create(notas), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica una nota", response = HttpStatus.class, tags = "Notas")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody NotaDTO notasModified) {
        try {
            Optional<NotaDTO> notUpdated = notasService.update(notasModified, id);
            if (notUpdated.isPresent()) {
                return new ResponseEntity<>(notUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina una nota", response = HttpStatus.class, tags = "Notas")
    @PreAuthorize("hasAuthority('USU03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            notasService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todas las notas", response = HttpStatus.class, tags = "Notas")
    @PreAuthorize("hasAuthority('USU03')")
    public ResponseEntity<?> deleteAll() {
        try {
            notasService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{titulo}")
    @ApiOperation(value = "Obtiene una nota a traves de su titulo", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByTitulo(@PathVariable(value = "titulo") String titulo) {
        try {
            Optional<Nota> notasFound = notasService.findByTitulo(titulo);
            if (notasFound.isPresent()) {
                NotaDTO notasDto = MapperUtils.DtoFromEntity(notasFound.get(), NotaDTO.class);
                return new ResponseEntity<>(notasDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
