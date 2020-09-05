
package org.una.tramites.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Jeffry
 */

@Entity
@Table(name = "Tramites_Cambios_Estados");
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteCambioEstado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "tramitesRegistrados_id")
    private TramitesRegistrados TramitesRegistrados;
    
    @ManyToOne
    @JoinColumn(name = "tramitesEstados_id")
    private TramitesEstados TramitesEstados;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
}

