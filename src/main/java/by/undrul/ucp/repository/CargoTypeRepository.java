package by.undrul.ucp.repository;

import by.undrul.ucp.entity.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoTypeRepository extends JpaRepository<CargoType,Long> {
    CargoType findByType(String type);
}
