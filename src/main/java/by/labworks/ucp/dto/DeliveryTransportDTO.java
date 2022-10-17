package by.labworks.ucp.dto;

import by.labworks.ucp.dto.AbstractDTO;
import by.labworks.ucp.dto.CityDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DeliveryTransportDTO extends AbstractDTO {
    private Double weight;
    private Double freeWeight;
    private CityDTO cityA;
    private CityDTO cityB;
}
