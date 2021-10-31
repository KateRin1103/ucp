package by.undrul.ucp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Getter
@Setter
public class RouteDTO extends AbstractDTO {

    private String name;

    @NotNull(message = "Место отправки должно быть выбрано!")
    private CityDTO city_a;

    @NotNull(message = "Место доставки должно быть выбрано!")
    private CityDTO city_b;

    private double distance;

    @Null
    private Set<CityDTO> cities;
}
