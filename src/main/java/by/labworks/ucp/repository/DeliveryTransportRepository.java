package by.labworks.ucp.repository;

import by.labworks.ucp.entity.DeliveryTransport;
import by.labworks.ucp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryTransportRepository extends JpaRepository<DeliveryTransport,Long> {
    Optional<DeliveryTransport> findById(Long id);

    List<DeliveryTransport> findAll();
}
