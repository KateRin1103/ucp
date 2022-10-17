package by.labworks.ucp.repository;

import by.labworks.ucp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findById(Long id);

    List<Order> findByCityA_NameAndAndCityB_Name(String cityA, String cityB);
}
