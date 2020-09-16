/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
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
    private int grupo;
    private String descripcion;
    private boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private java.util.Date fechaRegistro;
    private TramiteTipoDTO tramitesTipos; 
//    private List<RequisitoDTO> requisitos = new ArrayList<>();
}
