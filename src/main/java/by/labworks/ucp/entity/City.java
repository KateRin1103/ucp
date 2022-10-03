package by.labworks.ucp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class City extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "cities")
    private Set<Route> routes;
}
