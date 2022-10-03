package by.labworks.ucp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RouteDTO extends AbstractDTO {

    private String name;
    @NotNull(message = "Место отправки должно быть выбрано!")
    @Column(name = "city_a_id")
    private CityDTO cityA;
    @NotNull(message = "Место доставки должно быть выбрано!")
    @Column(name = "city_b_id")
    private CityDTO cityB;
    private double distance;
    private DeliveryMethodDTO deliveryMethod;

    private Set<CityDTO> cities;
}
