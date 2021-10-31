package by.undrul.ucp.service.impl;

import by.undrul.ucp.dto.CargoDTO;
import by.undrul.ucp.dto.mapper.CargoMapper;
import by.undrul.ucp.entity.Cargo;
import by.undrul.ucp.entity.Company;
import by.undrul.ucp.exception.ResourceNotFoundException;
import by.undrul.ucp.repository.CargoRepository;
import by.undrul.ucp.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    private final CargoMapper cargoMapper;
    private final CargoRepository cargoRepository;

    @Autowired
    public CargoServiceImpl(CargoMapper cargoMapper,
                                CargoRepository cargoRepository){
        this.cargoMapper=cargoMapper;
        this.cargoRepository=cargoRepository;
    }

    @Override
    public CargoDTO save(CargoDTO cargoDTO) {

        Cargo cargo = cargoMapper.toEntity(cargoDTO);
        cargoRepository.save(cargo);
        return cargoDTO;
    }

    @Override
    public void update(CargoDTO cargoDTO) {
        Cargo cargo = cargoRepository.findById(cargoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(cargoDTO.getId()));

        cargoRepository.save(cargoMapper.toEntity(cargoDTO));
    }

    @Override
    public void delete(Long cargoId) {
        Cargo cargo = cargoRepository.findById(cargoId).orElseThrow(() -> new ResourceNotFoundException(cargoId));
        cargoRepository.delete(cargo);
    }

    @Override
    public List<CargoDTO> findAll() {

        List<Cargo> cargos = cargoRepository.findAll();
        return cargoMapper.toDtoList(cargos);
    }

    @Override
    public CargoDTO findById(Long id) {

        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return cargoMapper.toDto(cargo);
    }

    @Override
    public CargoDTO findByName(String name) {

        Cargo cargo = cargoRepository.findByName(name);
        return cargoMapper.toDto(cargo);
    }

    @Override
    public CargoDTO findByCargoType(String type) {

        Cargo cargo = cargoRepository.findByCargoType(type);
        return cargoMapper.toDto(cargo);
    }
}
