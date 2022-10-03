package by.labworks.ucp.service;

import by.labworks.ucp.dto.CargoTypeDTO;

import java.util.List;

public interface CargoTypeService {
    List<CargoTypeDTO> findAll();
    CargoTypeDTO findById(Long id);
    CargoTypeDTO findByType(String type);

}
