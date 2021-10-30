package by.undrul.ucp.repository;

import by.undrul.ucp.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findById(Long id);
}
