package by.undrul.ucp.service;

import by.undrul.ucp.dto.CityDTO;
import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.dto.RouteDTO;

import java.util.List;
import java.util.Map;

public interface RouteService {
    RouteDTO save(RouteDTO routeDto);

    void update(RouteDTO routeDto);

    void delete(Long routeId);

    List<RouteDTO> findAll();

    RouteDTO findById(Long id);

    RouteDTO findByName(String name);
    RouteDTO findByCityA(CityDTO cityA);
    RouteDTO findByCityB(CityDTO cityB);
    RouteDTO findByCityAAndCityB(CityDTO cityA,CityDTO cityB);



    Map<CompanyDTO,Map<List<CityDTO>,Double>> findRoute(CityDTO cityA, CityDTO cityB);
}
