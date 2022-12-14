package by.labworks.ucp.service;

import by.labworks.ucp.dto.PageWrapper;
import by.labworks.ucp.dto.SignUpRequest;
import by.labworks.ucp.dto.UserDTO;

import java.util.List;

public interface UserService {
    void signUp(SignUpRequest signUpRequest);

    UserDTO save(UserDTO userDto);

    UserDTO findByUsername(String username);

    void update(UserDTO userDto);

    PageWrapper<UserDTO> findAll(int page, int size);

    List<UserDTO> findAll();

    UserDTO findById(Long id);
}
