package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.repositories.ITransaccionRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author Luis
 */
@Service
public class TransaccionServiceImplementation implements ITransaccionService{
    
    @Autowired
    private ITransaccionRepository transaccionRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(transaccionRepository.findById(id), TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findAll() {
        return ServiceConvertionHelper.findList(transaccionRepository.findAll(), TransaccionDTO.class);
    }

    @Override
    @Transactional
    public TransaccionDTO create(TransaccionDTO transaccion) {
        Transaccion transac = MapperUtils.EntityFromDto(transaccion, Transaccion.class);
        transac = transaccionRepository.save(transac);
        return MapperUtils.DtoFromEntity(transac, TransaccionDTO.class);
    }

    @Override
    @Transactional
    public Optional<TransaccionDTO> update(TransaccionDTO transaccion, Long id) {
        if(transaccionRepository.findById(id).isPresent()){
            Transaccion transac = MapperUtils.EntityFromDto(transaccion, Transaccion.class);
            transac = transaccionRepository.save(transac);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(transac, TransaccionDTO.class));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        transaccionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        transaccionRepository.deleteAll();
    }
    
}
