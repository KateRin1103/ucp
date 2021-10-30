package by.undrul.ucp.dto.mapper;

import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper extends AbstractMapper<Route, RouteDTO> {

    @Autowired
    public RouteMapper() {
        super(Route.class, RouteDTO.class);
    }
}
