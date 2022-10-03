package by.labworks.ucp.dto.converter;

import by.labworks.ucp.dto.RouteDTO;
import by.labworks.ucp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("routeDtoConverter")
public class RouteDtoConverter implements Converter<String, RouteDTO> {

    private final RouteService routeService;

    @Autowired
    public RouteDtoConverter(RouteService routeService){
        this.routeService=routeService;
    }

    @Override
    public RouteDTO convert(String id) {
        return routeService.findById(Long.parseLong(id));
    }
}
