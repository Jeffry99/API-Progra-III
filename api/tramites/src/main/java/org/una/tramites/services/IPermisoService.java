
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author Luis
 */
public interface IPermisoService {
    
    public Optional<PermisoDTO> findById(Long id);
    
    public Optional<List<PermisoDTO>> findByEstado(boolean estado);
    
    public Optional<PermisoDTO> findByCodigo(String Codigo);
    
    public Optional<List<PermisoDTO>> findByCodigoAproximate(String codigo);
    
    public Optional<List<PermisoDTO>> findAll();
    
    public PermisoDTO create(PermisoDTO permiso);
    
    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
