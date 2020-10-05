
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
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.services.ITransaccionService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Luis
 */
@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {
    
    @Autowired
    private ITransaccionService transaccionService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las transacciones", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRA06')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(transaccionService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una transaccion a traves de su identificador unico", response = TransaccionDTO.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRA05')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(transaccionService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea una transaccion", response = HttpStatus.class, tags = "Transacciones")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA01')")
    public ResponseEntity<?> create(@RequestBody TransaccionDTO tran, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(transaccionService.create(tran), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }   

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica una transaccion", response = HttpStatus.class, tags = "Transacciones")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRA02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TransaccionDTO tranModified) {
        try {
            Optional<TransaccionDTO> perUpdated = transaccionService.update(tranModified, id);
            if (perUpdated.isPresent()) {
                return new ResponseEntity<>(perUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina una transaccion", response = HttpStatus.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
         try {
            transaccionService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todas las transacciones", response = HttpStatus.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRA03')")
    public ResponseEntity<?> deleteAll() {
        try {
            transaccionService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
