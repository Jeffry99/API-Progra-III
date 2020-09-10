/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.RequisitoPresentado;

/**
 *
 * @author Pablo-VE
 */
public interface IRequisitoPresentadoRepository extends JpaRepository<RequisitoPresentado, Long> {
    public List<RequisitoPresentado>findByRequisito(Long id);
    
    public List<RequisitoPresentado>findByTramitesRegistrados(Long id);
    
}
