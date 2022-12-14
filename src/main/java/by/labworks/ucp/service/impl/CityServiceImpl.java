package by.labworks.ucp.service.impl;

import by.labworks.ucp.dto.CityDTO;
import by.labworks.ucp.dto.mapper.CityMapper;
import by.labworks.ucp.entity.City;
import by.labworks.ucp.exception.ResourceNotFoundException;
import by.labworks.ucp.repository.CityRepository;
import by.labworks.ucp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityMapper cityMapper, CityRepository cityRepository){
        this.cityMapper=cityMapper;
        this.cityRepository=cityRepository;
    }

    @Override
    @Transactional
    public CityDTO save(CityDTO cityDto) {

        City city = cityMapper.toEntity(cityDto);
        cityRepository.save(city);
        return cityDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.toDtoList(cities);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return cityMapper.toDto(city);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO findByCityName(String cityName) {
        City city = cityRepository.findByName(cityName);
        return cityMapper.toDto(city);
    }
}
