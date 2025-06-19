package pl.atins.sos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Department extends BaseEntity {

    @Column(name = "head_of_department", nullable = false)
    private String headOfDepartment;

    @Column(name = "localization", nullable = false)
    private String localization;

    @Column(name = "name", nullable = false)
    private String name;

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "headOfDepartment='" + headOfDepartment + '\'' +
                ", localization='" + localization + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
