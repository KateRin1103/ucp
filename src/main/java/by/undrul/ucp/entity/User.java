package by.undrul.ucp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(blocked, user.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, firstName, lastName, email, blocked);
    }

}
