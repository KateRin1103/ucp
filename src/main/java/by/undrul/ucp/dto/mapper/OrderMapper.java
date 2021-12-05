package by.undrul.ucp.dto.mapper;

import by.undrul.ucp.dto.OrderDTO;
import by.undrul.ucp.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDTO> {
    @Autowired
    public OrderMapper(){super(Order.class,OrderDTO.class);}
}
