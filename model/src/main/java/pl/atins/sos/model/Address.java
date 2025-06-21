package pl.atins.sos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "is_default", nullable = true)
    private boolean isDefault;

    @Column(name = "flat", nullable = true)
    private String flat;

    @Column(name = "house", nullable = false)
    private String house;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "street", nullable = true)
    private String street;

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "isDefault=" + isDefault +
                ", flat='" + flat + '\'' +
                ", house='" + house + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
    
}
