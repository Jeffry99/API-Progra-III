/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Pablo-VE
 */
@Entity
@Table(name = "tramites_estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(length = 100)
    private String descripcion;
    
    @Column(length = 10)
    private String estadosSucesores;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramitesEstados") 
    private List<TramiteCambioEstado> tramitesCambioEstados= new ArrayList<>();
}
