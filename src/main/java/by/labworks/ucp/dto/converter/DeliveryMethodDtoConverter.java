package by.labworks.ucp.dto.converter;

import by.labworks.ucp.dto.DeliveryMethodDTO;
import by.labworks.ucp.service.DeliveryMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("deliveryMethodDtoConverter")
public class DeliveryMethodDtoConverter implements Converter<String, DeliveryMethodDTO> {
    private final DeliveryMethodService deliveryMethodService;

    @Autowired
    public DeliveryMethodDtoConverter(DeliveryMethodService deliveryMethodService){
        this.deliveryMethodService=deliveryMethodService;
    }

    @Override
    public DeliveryMethodDTO convert(String id) {
        return deliveryMethodService.findById(Long.parseLong(id));
    }
}
