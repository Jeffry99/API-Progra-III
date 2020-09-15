/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Departamento;

/**
 *
 * @author Pablo-VE
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class UsuarioDTO {
 
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private boolean estado; 
    private String passwordEncriptado;
    
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaModificacion; 
    
    private boolean esJefe;
    private DepartamentoDTO departamento;
    
    
   
     
}


