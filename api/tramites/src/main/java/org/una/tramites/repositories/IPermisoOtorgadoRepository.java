
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author Luis
 */
public interface IPermisoOtorgadoRepository extends JpaRepository<PermisoOtorgado, Long>{
    
    
    @Query("SELECT p FROM PermisoOtorgado p LEFT JOIN p.usuario u LEFT JOIN p.permiso j WHERE u.id = :usuarioID AND j.id = :permisoID")
    public PermisoOtorgado findByUsuarioIdAndPermisoId(@Param("usuarioID") Long usuario, @Param("permisoID")Long permiso);
    
    @Query("SELECT p FROM PermisoOtorgado p LEFT JOIN p.usuario u WHERE u.id = :usuarioID")
    public List<PermisoOtorgado> findByUsuario(@Param("usuarioID") Long usuarioId);
    
    public List<PermisoOtorgado> findByPermiso(Long permisoId);
    
    
    public List<PermisoOtorgado> findByFechaRegistro(Date fechaRegistro);
}
