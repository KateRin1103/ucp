package by.labworks.ucp.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends AbstractDTO {
    private CargoDTO cargo;
    private CompanyDTO company;
    private UserDTO customer;
    private LocalDate createDate;
    private double cost;
    private CityDTO cityA;
    private CityDTO cityB;
    private DeliveryTransportDTO transport;
}
