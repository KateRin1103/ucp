package by.labworks.ucp.service.impl;

import by.labworks.ucp.dto.DeliveryTransportDTO;
import by.labworks.ucp.dto.mapper.DeliveryTransportMapper;
import by.labworks.ucp.entity.DeliveryTransport;
import by.labworks.ucp.exception.ResourceNotFoundException;
import by.labworks.ucp.repository.DeliveryTransportRepository;
import by.labworks.ucp.service.DeliveryTransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryTransportServiceImpl implements DeliveryTransportService {
    private final DeliveryTransportMapper mapper;
    private final DeliveryTransportRepository repository;

    @Autowired
    public DeliveryTransportServiceImpl(DeliveryTransportMapper mapper,
                                        DeliveryTransportRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<DeliveryTransportDTO> findAll() {
        List<DeliveryTransport> methods = repository.findAll();
        return mapper.toDtoList(methods);
    }

    @Override
    public DeliveryTransportDTO findById(Long id) {
        DeliveryTransport deliveryMethod = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toDto(deliveryMethod);
    }

    public void save(DeliveryTransportDTO deliveryTransportDTO){
        repository.save(mapper.toEntity(deliveryTransportDTO));
    }
}
