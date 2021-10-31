package by.undrul.ucp.dto.mapper;

import by.undrul.ucp.dto.DeliveryMethodDTO;
import by.undrul.ucp.entity.DeliveryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMethodMapper extends AbstractMapper<DeliveryMethod, DeliveryMethodDTO> {

    @Autowired
    public DeliveryMethodMapper(){super(DeliveryMethod.class,DeliveryMethodDTO.class);}
}
