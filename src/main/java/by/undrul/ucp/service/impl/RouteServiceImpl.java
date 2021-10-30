package by.undrul.ucp.service.impl;

import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.dto.mapper.RouteMapper;
import by.undrul.ucp.entity.City;
import by.undrul.ucp.entity.Route;
import by.undrul.ucp.exception.ResourceNotFoundException;
import by.undrul.ucp.repository.RouteRepository;
import by.undrul.ucp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteMapper routeMapper;
    private final RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteMapper routeMapper, RouteRepository routeRepository) {
        this.routeMapper = routeMapper;
        this.routeRepository = routeRepository;

    }


    @Override
    @Transactional
    public RouteDTO save(RouteDTO routeDto) {
        Route route = routeMapper.toEntity(routeDto);
        routeRepository.save(route);
        return routeDto;
    }

    @Override
    public void update(RouteDTO routeDto) {
        Route route = routeRepository.findById(routeDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(routeDto.getId()));

        routeRepository.save(routeMapper.toEntity(routeDto));
    }

    @Override
    public void delete(Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new ResourceNotFoundException(routeId));
        routeRepository.delete(route);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteDTO> findAll() {
        List<Route> routes = routeRepository.findAll();
        return routeMapper.toDtoList(routes);
    }

    @Override
    @Transactional(readOnly = true)
    public RouteDTO findById(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return routeMapper.toDto(route);
    }
}
