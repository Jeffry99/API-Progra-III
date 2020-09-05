/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
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
public class TramiteCambioEstadoDTO {
    private Long id;
    private Long Usuario_Id;
    private Long TramitesRegistrados;
    private Long TramitesEstados;
    private Date fechaRegistro;
}
