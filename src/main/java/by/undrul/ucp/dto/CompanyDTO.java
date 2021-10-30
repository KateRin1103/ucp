package by.undrul.ucp.dto;

import java.util.Objects;

public class CompanyDTO extends AbstractDTO{
    private String name;
    private String email;
    private String description;
    private double price_kg;
    private double price_km;

    public CompanyDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice_kg() {
        return price_kg;
    }

    public void setPrice_kg(double price_kg) {
        this.price_kg = price_kg;
    }

    public double getPrice_km() {
        return price_km;
    }

    public void setPrice_km(double price_km) {
        this.price_km = price_km;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDTO that = (CompanyDTO) o;
        return Double.compare(that.price_kg, price_kg) == 0 && Double.compare(that.price_km, price_km) == 0 && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, description, price_kg, price_km);
    }
}
