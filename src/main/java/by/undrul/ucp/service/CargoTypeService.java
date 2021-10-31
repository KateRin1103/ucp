package by.undrul.ucp.service;

import by.undrul.ucp.dto.CargoTypeDTO;

import java.util.List;

public interface CargoTypeService {
    List<CargoTypeDTO> findAll();
    CargoTypeDTO findById(Long id);
    CargoTypeDTO findByType(String type);

}
