package by.undrul.ucp.service;

import by.undrul.ucp.dto.DeliveryMethodDTO;

import java.util.List;

public interface DeliveryMethodService {
    List<DeliveryMethodDTO> findAll();

    DeliveryMethodDTO findById(Long id);

    DeliveryMethodDTO findByMethod(String method);
}
