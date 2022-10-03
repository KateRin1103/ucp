package by.labworks.ucp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CompanyDTO extends AbstractDTO {
    private String name;
    private String email;
    private String description;
    private double price_kg;
    private double price_km;
    private UserDTO user;
    private List<RouteDTO> routes;

}
