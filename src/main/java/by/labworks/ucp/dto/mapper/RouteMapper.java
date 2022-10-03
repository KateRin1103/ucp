package by.labworks.ucp.dto.mapper;

import by.labworks.ucp.dto.RouteDTO;
import by.labworks.ucp.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper extends AbstractMapper<Route, RouteDTO> {

    @Autowired
    public RouteMapper() {
        super(Route.class, RouteDTO.class);
    }
}
