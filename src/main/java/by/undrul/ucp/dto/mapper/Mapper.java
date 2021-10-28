package by.undrul.ucp.dto.mapper;



import by.undrul.ucp.dto.AbstractDTO;
import by.undrul.ucp.entity.AbstractEntity;

import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDTO> {

    E toEntity(D d);

    D toDto(E e);

    List<D> toDtoList(List<E> eList);
}
