package by.undrul.ucp.dto.mapper;

import by.undrul.ucp.dto.CargoDTO;
import by.undrul.ucp.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoMapper extends AbstractMapper<Cargo, CargoDTO>{
    @Autowired
    public CargoMapper(){super(Cargo.class,CargoDTO.class);}
}
