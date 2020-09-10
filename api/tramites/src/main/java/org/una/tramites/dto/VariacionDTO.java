/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Jeffry
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class VariacionDTO {
    
    
    private Long id;
    private boolean grupo;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    private TramiteTipoDTO tramites; 
    private List<RequisitoDTO> requisitos = new ArrayList<>();
}
