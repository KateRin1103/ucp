package by.labworks.ucp.dto.mapper;


import by.labworks.ucp.dto.UserDTO;
import by.labworks.ucp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    @Autowired
    public UserMapper() {
        super(User.class, UserDTO.class);
    }
}
