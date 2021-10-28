package by.undrul.ucp.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
