package by.undrul.ucp.dto.converter;

import by.undrul.ucp.dto.CityDTO;
import by.undrul.ucp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("cityDtoConverter")
public class CityDtoConverter implements Converter<String, CityDTO> {
    private final CityService cityService;


    @Autowired
    public CityDtoConverter(CityService cityService){
        this.cityService=cityService;
    }

    @Override
    public CityDTO convert(String id) {
        return cityService.findById(Long.parseLong(id));
    }
}
