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

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "city_a_id")
    private City city_a;

    @ManyToOne
    @JoinColumn(name = "city_b_id")
    private City city_b;

    @Column(name = "distance")
    private double distance;

    @ManyToMany
    @JoinTable(name = "routes_cities", joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private Set<City> cities;


}
