package by.undrul.ucp.repository;

import by.undrul.ucp.entity.Company;
import by.undrul.ucp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Company findByName(String name);
    Company findByUser(User user);
}
