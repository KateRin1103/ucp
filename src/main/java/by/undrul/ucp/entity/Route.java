package by.undrul.ucp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter

public class Route extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_a_id")
    private City cityA;

    @ManyToOne
    @JoinColumn(name = "city_b_id")
    private City cityB;

    @Column(name = "distance")
    private double distance;

    @ManyToOne
    @JoinColumn(name = "delivery_method_id")
    private DeliveryMethod deliveryMethod;

    @ManyToMany(mappedBy = "routes")
    private List<Company> companies = new ArrayList<>();

}
