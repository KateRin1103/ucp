package by.undrul.ucp.dto.converter;

import by.undrul.ucp.dto.CompanyDTO;
import by.undrul.ucp.service.CompanyService;
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
