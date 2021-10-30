package by.undrul.ucp.dto.mapper;

import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper extends AbstractMapper<Company, CompanyDTO>{

    @Autowired
    public CompanyMapper(){super(Company.class,CompanyDTO.class);}
}
