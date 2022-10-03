package by.labworks.ucp.dto.converter;

import by.labworks.ucp.dto.CargoDTO;
import by.labworks.ucp.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("cargoDtoConverter")
public class CargoDtoConverter implements Converter<String, CargoDTO> {
    private final CargoService cargoService;

    @Autowired
    public CargoDtoConverter(CargoService cargoService){
        this.cargoService=cargoService;
    }

    @Override
    public CargoDTO convert(String id) {
        return cargoService.findById(Long.parseLong(id));
    }
}
