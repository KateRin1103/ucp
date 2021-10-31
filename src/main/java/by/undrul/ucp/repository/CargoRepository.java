package by.undrul.ucp.repository;

import by.undrul.ucp.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo,Long> {
    Cargo findByName(String name);
    Cargo findByCargoType(String type);
}
