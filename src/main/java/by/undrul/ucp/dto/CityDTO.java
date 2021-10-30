package by.undrul.ucp.dto;

import java.util.Objects;

public class CityDTO extends AbstractDTO {
    private String name;

    public CityDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return Objects.equals(name, cityDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
