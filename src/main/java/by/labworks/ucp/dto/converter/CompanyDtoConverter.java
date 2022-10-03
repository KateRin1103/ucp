package by.labworks.ucp.dto.converter;

import by.labworks.ucp.dto.CompanyDTO;
import by.labworks.ucp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("companyDtoConverter")
public class CompanyDtoConverter implements Converter<String, CompanyDTO> {

    private CompanyService companyService;

    @Autowired
    CompanyDtoConverter(CompanyService companyService){
        this.companyService=companyService;
    }

    @Override
    public CompanyDTO convert(String id) {
        return companyService.findById(Long.parseLong(id));
    }
}
