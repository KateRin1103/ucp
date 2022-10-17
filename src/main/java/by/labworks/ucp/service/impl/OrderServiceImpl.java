package by.labworks.ucp.service.impl;

import by.labworks.ucp.dto.OrderDTO;
import by.labworks.ucp.dto.mapper.OrderMapper;
import by.labworks.ucp.entity.Order;
import by.labworks.ucp.exception.ResourceNotFoundException;
import by.labworks.ucp.repository.OrderRepository;
import by.labworks.ucp.service.OrderService;
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


    @Override
    public List<OrderDTO> findByCityAAndCityB(String a, String b) {
        return orderMapper.toDtoList(orderRepository.findByCityA_NameAndAndCityB_Name(a,b));
    }
}
