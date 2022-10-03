package by.labworks.ucp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cargos")
@Getter
@Setter
public class Cargo extends AbstractEntity{

    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private CargoType cargoType;
    @Column(name="weight")
    private double weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cargo cargo = (Cargo) o;
        return Double.compare(cargo.weight, weight) == 0 && Objects.equals(name, cargo.name) && Objects.equals(cargoType, cargo.cargoType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, cargoType, weight);
    }
}
