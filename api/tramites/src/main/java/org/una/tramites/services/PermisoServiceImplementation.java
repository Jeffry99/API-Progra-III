package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.repositories.IPermisoRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Luis
 */
@Service
public class PermisoServiceImplementation implements IPermisoService {
    
    @Autowired
    private IPermisoRepository permisoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(permisoRepository.findByEstado(estado), PermisoDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(permisoRepository.findById(id), PermisoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findAll() {
        return ServiceConvertionHelper.findList(permisoRepository.findAll(), PermisoDTO.class);
    }

    @Override
    @Transactional
    public PermisoDTO create(PermisoDTO permiso) {
        Permiso per = MapperUtils.EntityFromDto(permiso, Permiso.class);
        per = permisoRepository.save(per);
        return MapperUtils.DtoFromEntity(per, PermisoDTO.class);
    }

    @Override
    @Transactional
    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id) {
        if(permisoRepository.findById(id).isPresent()){
            Permiso per = MapperUtils.EntityFromDto(permiso, Permiso.class);
            per = permisoRepository.save(per);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(per, PermisoDTO.class));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoRepository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findByCodigo(String Codigo) {
        return ServiceConvertionHelper.oneToOptionalDto(Optional.ofNullable(permisoRepository.findByCodigo(Codigo)), PermisoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByCodigoAproximate(String codigo) {
        return ServiceConvertionHelper.findList(permisoRepository.findByCodigoContaining(codigo), PermisoDTO.class);
    }
    
}
