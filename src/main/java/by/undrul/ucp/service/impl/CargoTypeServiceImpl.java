package by.undrul.ucp.service.impl;

import by.undrul.ucp.dto.CargoTypeDTO;
import by.undrul.ucp.dto.mapper.CargoTypeMapper;
import by.undrul.ucp.entity.CargoType;
import by.undrul.ucp.exception.ResourceNotFoundException;
import by.undrul.ucp.repository.CargoTypeRepository;
import by.undrul.ucp.service.CargoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CargoTypeServiceImpl implements CargoTypeService {

    private final CargoTypeMapper cargoTypeMapper;
    private final CargoTypeRepository cargoTypeRepository;

    @Autowired
    public CargoTypeServiceImpl(CargoTypeMapper cargoTypeMapper,
                                CargoTypeRepository cargoTypeRepository) {
        this.cargoTypeMapper = cargoTypeMapper;
        this.cargoTypeRepository = cargoTypeRepository;
    }


    @Override
    public List<CargoTypeDTO> findAll() {
        List<CargoType> types = cargoTypeRepository.findAll();
        return cargoTypeMapper.toDtoList(types);
    }

    @Override
    public CargoTypeDTO findById(Long id) {
        CargoType type = cargoTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return cargoTypeMapper.toDto(type);
    }

    @Override
    public CargoTypeDTO findByType(String type) {
        CargoType cargoType = cargoTypeRepository.findByType(type);
        return cargoTypeMapper.toDto(cargoType);
    }
}
