package by.labworks.ucp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Company extends AbstractEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "description")
    private String description;
    @Column(name = "price_kg")
    private double price_kg;
    @Column(name = "price_km")
    private double price_km;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "companies_routes",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    private List<Route> routes = new ArrayList<>();
}
