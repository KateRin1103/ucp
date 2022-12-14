package by.labworks.ucp.service;

import by.labworks.ucp.dto.OrderDTO;
import by.labworks.ucp.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDTO save(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(Long orderId);

    List<OrderDTO> findAll();

    OrderDTO findById(Long id);

    List<OrderDTO> findByCityAAndCityB(String a, String b);
}
