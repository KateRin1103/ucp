package by.labworks.ucp.repository;

import by.labworks.ucp.entity.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoTypeRepository extends JpaRepository<CargoType,Long> {
    CargoType findByType(String type);
}
