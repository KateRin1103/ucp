package by.undrul.ucp.service.impl;

import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.dto.RouteDTO;
import by.undrul.ucp.dto.mapper.CompanyMapper;
import by.undrul.ucp.entity.Company;
import by.undrul.ucp.entity.Route;
import by.undrul.ucp.exception.ResourceNotFoundException;
import by.undrul.ucp.repository.CompanyRepository;
import by.undrul.ucp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private  final CompanyMapper companyMapper;
    private  final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper,
                              CompanyRepository companyRepository){
        this.companyMapper=companyMapper;
        this.companyRepository=companyRepository;
    }

    @Override
    @Transactional
    public CompanyDTO save(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        companyRepository.save(company);
        return companyDTO;
    }

    @Override
    public void update(CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(companyDTO.getId()));

        companyRepository.save(companyMapper.toEntity(companyDTO));
    }

    @Override
    public void delete(Long companyId) {
       Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException(companyId));
        companyRepository.delete(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyDTO> findAll() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.toDtoList(companies);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDTO findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDTO findByName(String name) {
        Company company = companyRepository.findByName(name);
        return companyMapper.toDto(company);
    }
}
