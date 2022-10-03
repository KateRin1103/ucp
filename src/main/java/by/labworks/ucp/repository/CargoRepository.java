package by.labworks.ucp.repository;

import by.labworks.ucp.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo,Long> {
    Cargo findByName(String name);
    Cargo findByCargoType(String type);
}
