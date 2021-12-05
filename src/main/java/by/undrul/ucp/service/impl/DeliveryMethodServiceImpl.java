package by.undrul.ucp.service.impl;

import by.undrul.ucp.dto.DeliveryMethodDTO;
import by.undrul.ucp.dto.mapper.DeliveryMethodMapper;
import by.undrul.ucp.entity.DeliveryMethod;
import by.undrul.ucp.exception.ResourceNotFoundException;
import by.undrul.ucp.repository.DeliveryMethodRepository;
import by.undrul.ucp.service.DeliveryMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryMethodServiceImpl implements DeliveryMethodService {
    private final DeliveryMethodMapper mapper;
    private final DeliveryMethodRepository repository;

    @Autowired
    public DeliveryMethodServiceImpl(DeliveryMethodMapper mapper,
                                     DeliveryMethodRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<DeliveryMethodDTO> findAll() {
        List<DeliveryMethod> methods = repository.findAll();
        return mapper.toDtoList(methods);
    }

    @Override
    public DeliveryMethodDTO findById(Long id) {
        DeliveryMethod deliveryMethod = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toDto(deliveryMethod);
    }

    @Override
    public DeliveryMethodDTO findByName(String name) {
        DeliveryMethod deliveryMethod = repository.findByName(name);
        return mapper.toDto(deliveryMethod);
    }
}
