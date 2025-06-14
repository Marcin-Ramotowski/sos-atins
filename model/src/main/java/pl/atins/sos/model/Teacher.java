package pl.atins.sos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Teacher")
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    // TODO: Add Department once implemented

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "employment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "office_number", nullable = false)
    private String officeNumber;

    @Column(name = "title", nullable = false)
    private String title;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
