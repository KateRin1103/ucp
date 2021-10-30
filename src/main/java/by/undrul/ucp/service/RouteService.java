package by.undrul.ucp.service;

import by.undrul.ucp.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    RouteDTO save(RouteDTO routeDto);

    void update(RouteDTO routeDto);

    void delete(Long routeId);

    List<RouteDTO> findAll();

    RouteDTO findById(Long id);
}