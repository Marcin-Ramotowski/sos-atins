package pl.atins.sos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "TeacherSubject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

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

    public Set<Subject> getSubjects() {
        return subjects;
    }
}
