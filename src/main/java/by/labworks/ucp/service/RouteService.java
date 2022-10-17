package by.labworks.ucp.service;

import by.labworks.ucp.dto.CityDTO;
import by.labworks.ucp.dto.CompanyDTO;
import by.labworks.ucp.dto.RouteDTO;
import org.jgrapht.GraphPath;

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

    List<CityDTO> findPathCities(CityDTO cityA, CityDTO cityB);

    int findPathLength(CityDTO cityA, CityDTO cityB);

    List<CityDTO> findPathEdges(CityDTO cityA, CityDTO cityB);

    GraphPath findShortestPath(CityDTO cityA, CityDTO cityB);

    Map<CompanyDTO,Map<List<CityDTO>,Double>> findPathCites(CityDTO cityA, CityDTO cityB);
}
