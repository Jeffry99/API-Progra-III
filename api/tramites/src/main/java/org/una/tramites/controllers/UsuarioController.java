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
import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.services.IAutenticacionService;
import org.una.tramites.services.IUsuarioService;
import org.una.tramites.utils.MapperUtils;
/**
 *
 * @author Pablo-VE
 */
@RestController
@RequestMapping("/usuarios") 
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping() 
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Usuario>> result = usuarioService.findAll();
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") 
    @ApiOperation(value = "Obtiene un usuario por su id", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Usuario> usuarioFound = usuarioService.findById(id);
            if (usuarioFound.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Autowired
    private IAutenticacionService autenticacionService;
        
    @PostMapping("/cambioContrasena") 
    @ResponseBody
    @ApiOperation(value = "Obtiene permiso para cambiar de contraseña", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> solicitarCambioContrasena(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse = autenticacionService.login(authenticationRequest);
            if (authenticationResponse!=null) {
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/cedula/{term}") 
    @ApiOperation(value = "Obtiene una lista de usuarios por cedula", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByCedulaAproximate(term);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}") 
    @PreAuthorize("hasAuthority('USU04')")
    @ApiOperation(value = "Obtiene una lista de usuarios por nombre completo", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/departamento/{term}") 
    @ApiOperation(value = "Obtiene una lista de usuarios por departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "term") Long term) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByDepartamentoId(term);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/jefedepartamento/{id}") 
    @ApiOperation(value = "Obtiene un usuario jefe por departamento", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU04')")
    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "id") Long id) {
        try {

            Optional<Usuario> usuarioFound = usuarioService.findJefeByDepartamento(id);
            if (usuarioFound.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/") 
    @ApiOperation(value = "Crea un usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU01')")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioCreated = usuarioService.create(usuario);
            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, UsuarioDTO.class);
            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/cambiarContrasena/{id}") 
    @ApiOperation(value = "Cambia contrasena de un usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> cambiarContrasena(@PathVariable(value = "id") Long id, @RequestBody Usuario usuarioModified) {
        try {
            Optional<Usuario> usuarioUpdated = usuarioService.cambioContrasena(usuarioModified, id);
            if (usuarioUpdated.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}") 
    @ApiOperation(value = "Modifica un usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @ResponseBody
    @PreAuthorize("hasAuthority('USU02')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Usuario usuarioModified) {
        try {
            Optional<Usuario> usuarioUpdated = usuarioService.update(usuarioModified, id);
            if (usuarioUpdated.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}") 
    @ApiOperation(value = "Elimina un usuario", response = HttpStatus.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU03')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        //TODO: Implementar este método
        try{
                    usuarioService.delete(id);
                    if(findById(id).getStatusCode() == HttpStatus.NO_CONTENT){
                        return new ResponseEntity<>(HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }catch(Exception ex){
                    return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
                }
    }

    @DeleteMapping("/") 
    @ApiOperation(value = "Elimina todos los usuarios", response = HttpStatus.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USU03')")
    public ResponseEntity<?> deleteAll() {
 	//TODO: Implementar este método
        try{
            usuarioService.deleteAll();
            if(findAll().getStatusCode() == HttpStatus.NO_CONTENT){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 

}
