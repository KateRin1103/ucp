package by.undrul.ucp.service;

import by.undrul.ucp.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    CompanyDTO save(CompanyDTO companyDTO);

    void update(CompanyDTO companyDTO);

    void delete(Long companyId);

    List<CompanyDTO> findAll();

    CompanyDTO findById(Long id);

    CompanyDTO findByName(String name);
}
