package by.labworks.ucp.repository;

import by.labworks.ucp.dto.RouteDTO;
import by.labworks.ucp.entity.City;
import by.labworks.ucp.entity.Company;
import by.labworks.ucp.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findById(Long id);

    Route findByName(String name);

    Route findByCityA(City cityA);
    Route findByCityB(City cityB);

    Route findByCityAAndCityB(City cityA, City cityB);
}
