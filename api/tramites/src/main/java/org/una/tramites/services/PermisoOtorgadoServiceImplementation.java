
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;
import org.springframework.stereotype.Service;
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.repositories.IUsuarioRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Luis
 */
@Service
public class PermisoOtorgadoServiceImplementation implements IPermisoOtorgadoService{
    
    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;
        
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findAll() {
        return ServiceConvertionHelper.findList(permisoOtorgadoRepository.findAll(), PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoOtorgadoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(permisoOtorgadoRepository.findById(id), PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuario(Long id) {
        return ServiceConvertionHelper.findList(permisoOtorgadoRepository.findByUsuario(id), PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermiso(Long id) {
        return ServiceConvertionHelper.findList(permisoOtorgadoRepository.findByPermiso(id), PermisoOtorgadoDTO.class);
    }

    @Override
    public Optional<List<PermisoOtorgadoDTO>> findPermisoOtorgadoByfechaRegistro(Date fechaRegistro) {
        return ServiceConvertionHelper.findList(permisoOtorgadoRepository.findByFechaRegistro(fechaRegistro), PermisoOtorgadoDTO.class);
    }
    
    @Override
    @Transactional
    public PermisoOtorgadoDTO create(PermisoOtorgadoDTO permisoOtorgado, Long id) {
        PermisoOtorgado po = MapperUtils.EntityFromDto(permisoOtorgado, PermisoOtorgado.class);
        Usuario user = usuarioRepository.findById(id).get();
        po.setUsuario(user);
        po = permisoOtorgadoRepository.save(po);
        return MapperUtils.DtoFromEntity(po, PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisoOtorgadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoOtorgadoRepository.deleteAll();
    }

    @Override
    @Transactional
    public Optional<PermisoOtorgadoDTO> update(PermisoOtorgadoDTO permisoOtorgado, Long id, Long ID) {
        if (permisoOtorgadoRepository.findById(id).isPresent()) {
            PermisoOtorgado po = MapperUtils.EntityFromDto(permisoOtorgado, PermisoOtorgado.class);
            Usuario user = usuarioRepository.findById(ID).get();
            po.setUsuario(user);
            po = permisoOtorgadoRepository.save(po);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(po, PermisoOtorgadoDTO.class));
        } else {
            return null;
        }
    }
    
}
