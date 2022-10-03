package by.labworks.ucp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_methods")
@Getter
@Setter
@EqualsAndHashCode
public class DeliveryMethod extends AbstractEntity{
    @Column(name = "name")
    private String name;

}
