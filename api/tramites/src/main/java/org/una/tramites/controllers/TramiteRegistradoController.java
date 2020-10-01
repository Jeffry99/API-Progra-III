/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRU06')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity(tramitesRegistradosService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite registrado a traves de su identificador unico", response = TramiteRegistradoDTO.class, tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRU05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try{
            return new ResponseEntity<>(tramitesRegistradosService.findById(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un tramite registrado", response = HttpStatus.class, tags = "Tramites_Registrados")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRU01')")
    public ResponseEntity<?> create(@RequestBody TramiteRegistradoDTO tramiteRegistrado) {
        try{
            return new ResponseEntity<>(tramitesRegistradosService.create(tramiteRegistrado), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un tramite registrado", response = HttpStatus.class, tags = "Tramites_Registrados")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TramiteRegistradoDTO tramiteRegistradoDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            try{
                Optional<TramiteRegistradoDTO> updated = tramitesRegistradosService.update(tramiteRegistradoDTO, id);
                if(updated.isPresent()){
                    return new ResponseEntity(updated, HttpStatus.OK);
                }else{
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            }catch(Exception ex){
                return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un tramite registrado", response = HttpStatus.class, tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRU03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramitesRegistradosService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los tramites registrados", response = HttpStatus.class, tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRU03')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramitesRegistradosService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     @GetMapping("/cliente/{id}")
    @ApiOperation(value = "Obtiene una lista de tramites registrados por un cliente", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TAR05')")
    public @ResponseBody ResponseEntity<?> findByClienteId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramitesRegistradosService.findByClienteId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tipo_tramite/{id}")
    @ApiOperation(value = "Obtiene una lista de tramites segun el tipo", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public @ResponseBody ResponseEntity<?> findByTipoTramiteId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramitesRegistradosService.findByTramiteTipoId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("filtro/{cedula}/{estado}/{inicio}/{fin}")
//    @ApiOperation(value = "Obtiene un tramite registrado a de distintos parametros", response = TramiteRegistradoDTO.class,  responseContainer = "List", tags = "Tramites_Registrados")
//    @PreAuthorize("hasAuthority('TAR05')")
//    public ResponseEntity<?> getByFilter(@PathVariable(value = "cedula")String cedula, @PathVariable(value = "estado")String estado, @PathVariable(value = "inicio")Date inicio, @PathVariable(value = "fin")Date fin){
//        try{
//            return new ResponseEntity<>(tramitesRegistradosService.getByFilter(cedula, estado, inicio, fin), HttpStatus.OK);
//        }catch(Exception ex){
//            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}

