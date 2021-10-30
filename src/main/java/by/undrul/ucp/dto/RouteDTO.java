package by.undrul.ucp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RouteDTO extends AbstractDTO {

    @NotNull(message = "Место отправки должно быть выбрано!")
    private CityDTO city_a;

    @NotNull(message = "Место доставки должно быть выбрано!")
    private CityDTO city_b;
}
