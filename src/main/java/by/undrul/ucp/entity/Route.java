package by.undrul.ucp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter

public class Route extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "city_a_id")
    private City city_a;

    @ManyToOne
    @JoinColumn(name = "city_b_id")
    private City city_b;

    @ManyToMany
    @JoinTable(name = "routes_cities", joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private Set<City> cities;
}
