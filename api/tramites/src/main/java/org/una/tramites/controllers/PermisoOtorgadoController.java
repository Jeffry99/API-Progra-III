
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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.services.IPermisoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Luis
 */

@RestController
@RequestMapping("/permisos_otorgados")
@Api(tags = {"Permisos_Otorgados"})
public class PermisoOtorgadoController {
    
    @Autowired
    private IPermisoOtorgadoService perOtorgadoService;
 
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('USU04')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(perOtorgadoService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso otorgado a traves de su identificador unico", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(perOtorgadoService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @GetMapping("/usuario_permiso/{usuario}/{permiso}")
//    @ApiOperation(value = "Obtiene un permiso otorgado por su permiso y usuario", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
//    @PreAuthorize("hasAuthority('USU04')")
//    public ResponseEntity<?> findByUsuarioIdAndPermisoId(@PathVariable(value = "usuario") Long usuario, @PathVariable(value = "permiso") Long permiso) {
//        try {
//            Optional<PermisoOtorgado> perOtorgadoFound = perOtorgadoService.findByUsuarioAndPermiso(usuario, permiso);
//            if (perOtorgadoFound.isPresent()) {
//                PermisoOtorgadoDTO perOtorDto = MapperUtils.DtoFromEntity(perOtorgadoFound.get(), PermisoOtorgadoDTO.class);
//                return new ResponseEntity<>(perOtorDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Crea un permiso otorgado", response = HttpStatus.class, tags = "Permisos_Otorgados")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU01')")
    public ResponseEntity<?> create(@RequestBody PermisoOtorgadoDTO perOtorgado, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(perOtorgadoService.create(perOtorgado), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        } 
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifica un permiso otorgado", response = HttpStatus.class, tags = "Permisos_Otorgados")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @PathVariable(value = "ID") Long ID, @RequestBody PermisoOtorgadoDTO perOtorgadoModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<PermisoOtorgadoDTO> perOtorgadoUpdated = perOtorgadoService.update(perOtorgadoModified, id, ID);
                if (perOtorgadoUpdated.isPresent()) {
                    return new ResponseEntity<>(perOtorgadoUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }    
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un permiso otorgado", response = HttpStatus.class, tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('USU03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            perOtorgadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los permisos otorgados", response = HttpStatus.class, tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('USU03')")
    public ResponseEntity<?> deleteAll() {
        try {
            perOtorgadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
