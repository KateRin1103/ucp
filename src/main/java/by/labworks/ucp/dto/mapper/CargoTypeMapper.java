package by.labworks.ucp.dto.mapper;

import by.labworks.ucp.dto.CargoTypeDTO;
import by.labworks.ucp.entity.CargoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoTypeMapper extends AbstractMapper<CargoType, CargoTypeDTO> {
    @Autowired
    public CargoTypeMapper(){super(CargoType.class,CargoTypeDTO.class);}
}
