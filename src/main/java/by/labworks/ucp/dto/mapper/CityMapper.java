package by.labworks.ucp.dto.mapper;

import by.labworks.ucp.dto.CityDTO;
import by.labworks.ucp.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper extends AbstractMapper<City, CityDTO> {

    @Autowired
    public CityMapper() {
        super(City.class, CityDTO.class);
    }
}
