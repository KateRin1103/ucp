package by.labworks.ucp.service;

import by.labworks.ucp.dto.CompanyDTO;
import by.labworks.ucp.dto.RouteDTO;
import by.labworks.ucp.dto.UserDTO;

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
