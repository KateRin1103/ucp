package by.labworks.ucp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cargo_types")
@Getter
@Setter
public class CargoType extends  AbstractEntity{
    @Column(name = "type")
    private String type;
}
