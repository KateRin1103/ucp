package by.labworks.ucp.dto.mapper;

import by.labworks.ucp.dto.DeliveryMethodDTO;
import by.labworks.ucp.entity.DeliveryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMethodMapper extends AbstractMapper<DeliveryMethod, DeliveryMethodDTO> {

    @Autowired
    public DeliveryMethodMapper(){super(DeliveryMethod.class,DeliveryMethodDTO.class);}
}
