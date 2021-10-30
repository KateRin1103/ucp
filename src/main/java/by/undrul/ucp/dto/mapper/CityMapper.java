package by.undrul.ucp.dto.mapper;

import by.undrul.ucp.dto.CityDTO;
import by.undrul.ucp.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper extends AbstractMapper<City, CityDTO> {

    @Autowired
    public CityMapper() {
        super(City.class, CityDTO.class);
    }
}
