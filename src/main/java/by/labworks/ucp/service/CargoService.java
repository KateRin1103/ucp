package by.labworks.ucp.service;

import by.labworks.ucp.dto.CargoDTO;

import java.util.List;

public interface CargoService {
    CargoDTO save(CargoDTO cargoDTO);

    void update(CargoDTO cargoDTO);

    void delete(Long cargoId);

    List<CargoDTO> findAll();

    CargoDTO findById(Long id);

    CargoDTO findByName(String name);

    CargoDTO findByCargoType(String type);
}
