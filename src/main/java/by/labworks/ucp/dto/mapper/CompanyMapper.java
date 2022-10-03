package by.labworks.ucp.dto.mapper;

import by.labworks.ucp.dto.CompanyDTO;
import by.labworks.ucp.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper extends AbstractMapper<Company, CompanyDTO>{

    @Autowired
    public CompanyMapper(){super(Company.class,CompanyDTO.class);}
}
