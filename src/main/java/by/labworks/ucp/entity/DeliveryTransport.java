package by.labworks.ucp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_transport")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DeliveryTransport extends AbstractEntity {
    @Column(name = "weight")
    private Double weight;

    @Column(name = "free_weight")
    private Double freeWeight;

    @ManyToOne
    @JoinColumn(name = "cityA_id")
    private City cityA;

    @ManyToOne
    @JoinColumn(name = "cityB_id")
    private City cityB;

    @OneToMany
    private List<Order> orders;
}
