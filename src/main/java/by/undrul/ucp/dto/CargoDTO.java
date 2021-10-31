package by.undrul.ucp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CargoDTO extends AbstractDTO{
    private String name;
    private CargoTypeDTO cargoType;
    private double weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoDTO cargoDTO = (CargoDTO) o;
        return Double.compare(cargoDTO.weight, weight) == 0 && Objects.equals(name, cargoDTO.name) && Objects.equals(cargoType, cargoDTO.cargoType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cargoType, weight);
    }
}
