package pl.atins.sos.model;

import jakarta.persistence.*;

@Entity
public class Transcript {

    @Id
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transcript{" +
                "id=" + id +
                ", student=" + student +
                '}';
    }
}
