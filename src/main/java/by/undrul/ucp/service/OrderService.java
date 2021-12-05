package by.undrul.ucp.service;

import by.undrul.ucp.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO save(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(Long orderId);

    List<OrderDTO> findAll();

    OrderDTO findById(Long id);

}
