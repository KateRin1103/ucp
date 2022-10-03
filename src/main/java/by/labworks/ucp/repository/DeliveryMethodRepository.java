package by.labworks.ucp.repository;

import by.labworks.ucp.entity.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod,Long> {
    DeliveryMethod findByName(String name);
}
