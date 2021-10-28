package by.undrul.ucp.service;

import by.undrul.ucp.dto.PageWrapper;
import by.undrul.ucp.dto.SignUpRequest;
import by.undrul.ucp.dto.UserDTO;
import by.undrul.ucp.entity.User;

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
