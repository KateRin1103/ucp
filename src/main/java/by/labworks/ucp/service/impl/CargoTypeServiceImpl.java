package by.labworks.ucp.service.impl;

import by.labworks.ucp.dto.CargoTypeDTO;
import by.labworks.ucp.dto.mapper.CargoTypeMapper;
import by.labworks.ucp.entity.CargoType;
import by.labworks.ucp.exception.ResourceNotFoundException;
import by.labworks.ucp.repository.CargoTypeRepository;
import by.labworks.ucp.service.CargoTypeService;
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
