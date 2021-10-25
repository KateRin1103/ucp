package by.undrul.ucp.service;

import by.undrul.ucp.entity.User;

public interface UserService {
    void save(User user);
    User findUserByUsername(String username);

}
