package by.undrul.ucp.dto;


import javax.validation.constraints.Null;
import java.util.Objects;
import java.util.Set;

public class UserDTO extends AbstractDTO {

    private String username;
    @Null
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer blocked;



    @Null
    private Set<RoleDTO> roles;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }


    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(blocked, userDTO.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, email, blocked);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
