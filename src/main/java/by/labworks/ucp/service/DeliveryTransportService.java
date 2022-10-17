package by.labworks.ucp.service;

import by.labworks.ucp.dto.DeliveryTransportDTO;

import java.util.List;

public interface DeliveryTransportService {
    List<DeliveryTransportDTO> findAll();

    DeliveryTransportDTO findById(Long id);

    void save(DeliveryTransportDTO d);
}
