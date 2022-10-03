package by.labworks.ucp.service;

import by.labworks.ucp.dto.DeliveryMethodDTO;

import java.util.List;

public interface DeliveryMethodService {
    List<DeliveryMethodDTO> findAll();

    DeliveryMethodDTO findById(Long id);

    DeliveryMethodDTO findByName(String name);
}
