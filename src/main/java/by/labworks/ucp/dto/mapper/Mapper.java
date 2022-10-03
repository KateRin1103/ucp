package by.labworks.ucp.dto.mapper;



import by.labworks.ucp.dto.AbstractDTO;
import by.labworks.ucp.entity.AbstractEntity;

import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDTO> {

    E toEntity(D d);

    D toDto(E e);

    List<D> toDtoList(List<E> eList);
}
