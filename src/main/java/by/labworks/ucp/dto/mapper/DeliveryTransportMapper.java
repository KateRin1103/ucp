package by.labworks.ucp.dto.mapper;

import by.labworks.ucp.dto.DeliveryTransportDTO;
import by.labworks.ucp.entity.DeliveryTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryTransportMapper extends AbstractMapper<DeliveryTransport, DeliveryTransportDTO> {

    @Autowired
    public DeliveryTransportMapper(){super(DeliveryTransport.class,DeliveryTransportDTO.class);}
}
