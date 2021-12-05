package by.undrul.ucp.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class DeliveryMethodDTO extends AbstractDTO {
    private String name;

}
