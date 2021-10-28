package by.undrul.ucp.dto;

import java.util.Objects;

public class RoleDTO extends AbstractDTO {

    private String name;

    public RoleDTO() {
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
        RoleDTO roleDto = (RoleDTO) o;
        return Objects.equals(name, roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
