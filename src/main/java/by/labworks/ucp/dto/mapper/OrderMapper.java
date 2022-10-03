package by.labworks.ucp.dto.mapper;

import by.labworks.ucp.dto.OrderDTO;
import by.labworks.ucp.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDTO> {
    @Autowired
    public OrderMapper(){super(Order.class,OrderDTO.class);}
}
