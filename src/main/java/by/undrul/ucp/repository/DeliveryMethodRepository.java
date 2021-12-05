package by.undrul.ucp.repository;

import by.undrul.ucp.entity.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryMethodRepository extends JpaRepository<DeliveryMethod,Long> {
    DeliveryMethod findByName(String name);
}
