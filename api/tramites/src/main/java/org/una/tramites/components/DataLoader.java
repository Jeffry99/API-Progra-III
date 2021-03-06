/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.components;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.entities.Usuario;
import org.una.tramites.loaders.Permisos;
import org.una.tramites.services.IUsuarioService;
import org.una.tramites.services.IPermisoService;
import org.una.tramites.services.IPermisoOtorgadoService;

/**
 *
 * @author Pablo-VE
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPermisoService permisoService;

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    @Override
    public void run(ApplicationArguments args) {
        
        if (usuarioService.findByCedula(cedula) == null) {
            
            createPermisos();
            PermisoDTO per;
            UsuarioDTO usuario = new UsuarioDTO();
            List<PermisoOtorgadoDTO> po = new ArrayList<>();
            PermisoOtorgadoDTO perotor;
            for(Permisos permiso : Permisos.values()){
                per = permisoService.findByCodigo(permiso.getCodigo()).get();
                perotor = new PermisoOtorgadoDTO();
                perotor.setPermiso(per);
                po.add(perotor);
            }
            usuario.setCedula(cedula);
            usuario.setPasswordEncriptado(password);
            usuario.setNombreCompleto("Usuario Administrador");
            usuario = usuarioService.create(usuario);
            for(PermisoOtorgadoDTO permiso :  po){
                permisoOtorgadoService.create(permiso); 
            }
//            Permiso permiso;
//            final String codigo = "Usu01"; 
//            Optional<Permiso> permisoBuscado = permisoService.findByCodigo(codigo);
//
//            if (permisoBuscado.isEmpty()) { 
//                permiso = new Permiso();
//                permiso.setCodigo(codigo);
//                permiso.setDescripcion("Registrar usuario nuevo");
//                permiso = permisoService.create(permiso);
//
//            } else {
//                permiso = permisoBuscado.get();
//            }
//            
//            createPermisos();
//            Usuario usuario = new Usuario();
//            usuario.setNombreCompleto("Usuario Admin");
//            usuario.setCedula(cedula);
//            usuario.setPasswordEncriptado(password);
//            usuario = usuarioService.create(usuario);
//
//            PermisoOtorgado permisoOtorgado = new PermisoOtorgado();
//            permisoOtorgado.setPermiso(permiso);
//            permisoOtorgado.setUsuario(usuario);
//            permisoOtorgadoService.create(permisoOtorgado);

            System.out.println("Se agrega el usuario inicial");
        } else {
            System.out.println("Se encontro el admin");
        }

        
    }
    
    private void createPermisos() {
        for (Permisos permiso : Permisos.values()) {
            PermisoDTO nuevoPermiso = new PermisoDTO();
            nuevoPermiso.setCodigo(permiso.getCodigo());
            nuevoPermiso.setDescripcion(permiso.name());
            if(permisoService.findByCodigo(permiso.getCodigo()) == null)
            permisoService.create(nuevoPermiso);
        } 
    }

}

