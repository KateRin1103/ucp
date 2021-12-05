package by.undrul.ucp.service;

import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.dto.UserDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO save(CompanyDTO companyDTO);

    void update(CompanyDTO companyDTO);

    void delete(Long companyId);

    List<CompanyDTO> findAll();

    CompanyDTO findById(Long id);

    CompanyDTO findByName(String name);
    CompanyDTO findByUser(UserDTO user);

    void addRoute(Long id, RouteDTO route);
}
