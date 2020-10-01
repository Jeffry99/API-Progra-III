/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author Pablo-VE
 */
public interface ITramiteRegistradoRepository extends JpaRepository<TramiteRegistrado, Long> {
    @Query("SELECT t FROM TramiteRegistrado t LEFT JOIN t.cliente d WHERE t.cliente.id =:id")
    public List<TramiteRegistrado> findByClientesIdContaining(Long id);
    public List<TramiteRegistrado> findByTramitesTiposIdContaining(Long id);
//    @Query("SELECT t FROM TramiteRegistrado t JOIN t.estados est on t.id = est.tramiteRegistrado.id "+
  //          "WHERE UPPER(t.cliente.cedula) like CONCAT('%', UPPER(:cedula), '%') "+
//            "and UPPER(est.tramiteEstado.nombre) Like CONCAT('%', UPPER(:estado), '%') and est.fechaRegistro BETWEEN :inicio and :fin")
    //public List<TramiteRegistrado> getByFilter(String cedula, String estado, Date inicio, Date fin);  
}
