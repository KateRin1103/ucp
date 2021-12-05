package by.undrul.ucp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RouteDTO extends AbstractDTO {

    private String name;
    @NotNull(message = "Место отправки должно быть выбрано!")
    private CityDTO cityA;
    @NotNull(message = "Место доставки должно быть выбрано!")
    private CityDTO cityB;
    private double distance;
    private DeliveryMethodDTO deliveryMethod;
}
