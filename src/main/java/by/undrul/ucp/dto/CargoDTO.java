package by.undrul.ucp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class CargoDTO extends AbstractDTO{
    private String name;
    private CargoTypeDTO cargoType;
    private double weight;
}
