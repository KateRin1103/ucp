package by.undrul.ucp.service;

import by.undrul.ucp.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO save(CityDTO CityDto);

    List<CityDTO> findAll();

    CityDTO findById(Long id);

    CityDTO findByCityName(String cityName);
}
