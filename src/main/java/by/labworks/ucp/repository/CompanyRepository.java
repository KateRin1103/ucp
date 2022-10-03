package by.labworks.ucp.repository;

import by.labworks.ucp.entity.Company;
import by.labworks.ucp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByName(String name);
    Company findByUser(User user);
}
