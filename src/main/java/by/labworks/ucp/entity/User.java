package by.labworks.ucp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User extends AbstractEntity{

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name", nullable = false, length = 48)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 48)
    private String lastName;
    @Column(name = "email", nullable = false, length = 48, unique = true)
    private String email;
    @Column(columnDefinition = "smallint")
    private Integer blocked;

  /*  @Transient
    private String confirmPassword;*/

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @PrePersist
    protected void onCreate() {
        blocked = 0;
    }

}
