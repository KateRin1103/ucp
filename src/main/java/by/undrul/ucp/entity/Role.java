package by.undrul.ucp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends AbstractEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }
}
