package by.undrul.ucp.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO extends AbstractDTO {
    private CargoDTO cargo;
    private RouteDTO route;
    private CompanyDTO company;
    private UserDTO customer;

    //TODO: Calculate cost

    /*private double cost = calculateCost();

    private   double calculateCost(){
        double cost=0;
        cost+=cargo.getWeight()*company.getPrice_kg();
        cost+=route.getDistance()*company.getPrice_km();
        return cost;
    }*/
}
