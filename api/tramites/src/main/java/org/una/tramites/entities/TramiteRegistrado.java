/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tramites_registrados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteRegistrado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="clientes_id")
    private Cliente cliente;
    
    @Column(name = "tramites_tipos_id")
    private int tramitesTiposId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramitesRegistrados") 
    private List<ArchivoRelacionado> archivosRelacionados = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramitesRegistrados") 
    private List<Nota> notas = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramitesRegistrados") 
    private List<RequisitoPresentado> requisitosPresentados = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramitesRegistrados") 
    private List<TramiteCambioEstado> tramitesCambioEstados= new ArrayList<>();
    
    
    private static final long serialVersionUID = 1L;
    
}