package pl.atins.sos.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Enrollment extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @Column(name = "status")
    private String status;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "subject=" + subject +
                ", student=" + student +
                ", enrollmentDate=" + enrollmentDate +
                ", status='" + status + '\'' +
                '}';
    }
}
