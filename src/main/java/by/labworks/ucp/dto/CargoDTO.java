package by.labworks.ucp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CargoDTO extends AbstractDTO{
    private String name;
    private CargoTypeDTO cargoType;
    private double weight;
}
