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
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author Pablo-VE
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TramiteCambioEstadoDTO {
    private Long id;
    private UsuarioDTO usuario;
    private TramiteRegistradoDTO tramitesRegistrados;
    private TramiteEstadoDTO tramitesEstados;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro; 
    
}
