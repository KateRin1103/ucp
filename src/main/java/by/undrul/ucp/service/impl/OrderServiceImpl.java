package by.undrul.ucp.service.impl;

import by.undrul.ucp.dto.OrderDTO;
import by.undrul.ucp.dto.mapper.OrderMapper;
import by.undrul.ucp.entity.Order;
import by.undrul.ucp.exception.ResourceNotFoundException;
import by.undrul.ucp.repository.OrderRepository;
import by.undrul.ucp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        orderRepository.save(order);
        return orderDTO;
    }

    @Override
    public void update(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(orderDTO.getId()));

        orderRepository.save(orderMapper.toEntity(orderDTO));
    }

    @Override
    public void delete(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(orderId));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDtoList(orders);
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return orderMapper.toDto(order);
    }


}
