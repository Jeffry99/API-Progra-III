package org.una.tramites.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.json.bind.annotation.JsonbDateFormat;
/**
 *
 * @author Luis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class PermisoOtorgadoDTO {
 
    private Long id; 
    private UsuarioDTO usuario;   
    private PermisoDTO permiso; 
    
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private java.util.Date fechaRegistro;  
    
    private boolean estado; 
//    private List<Transaccion> transacciones;
}
