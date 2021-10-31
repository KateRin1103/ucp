package by.undrul.ucp.dto.converter;

import by.undrul.ucp.dto.CargoTypeDTO;
import by.undrul.ucp.service.CargoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("cargoTypeDtoConverter")
public class CargoTypeDtoConverter implements Converter<String, CargoTypeDTO> {
    private final CargoTypeService cargoTypeService;

    @Autowired
    public CargoTypeDtoConverter(CargoTypeService cargoTypeService){
        this.cargoTypeService=cargoTypeService;
    }

    @Override
    public CargoTypeDTO convert(String id) {
        return cargoTypeService.findById(Long.parseLong(id));
    }
}
