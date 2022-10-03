package by.labworks.ucp.service;

import by.labworks.ucp.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO save(CityDTO CityDto);

    List<CityDTO> findAll();

    CityDTO findById(Long id);

    CityDTO findByCityName(String cityName);
}
