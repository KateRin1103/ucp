package by.labworks.ucp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "createDate")
    private LocalDate createDate;

    @Column(name = "cost")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "cityA_id")
    private City cityA;

    @ManyToOne
    @JoinColumn(name = "cityB_id")
    private City cityB;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private DeliveryTransport deliveryTransport;
}
