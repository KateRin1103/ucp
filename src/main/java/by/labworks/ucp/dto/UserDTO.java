package by.labworks.ucp.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
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

}
