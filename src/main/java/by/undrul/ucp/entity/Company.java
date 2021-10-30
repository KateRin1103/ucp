package by.undrul.ucp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "companies")
@Getter
@Setter
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

    public Company() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return Double.compare(company.price_kg, price_kg) == 0 && Double.compare(company.price_km, price_km) == 0 && Objects.equals(name, company.name) && Objects.equals(email, company.email) && Objects.equals(description, company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email, description, price_kg, price_km);
    }
}
