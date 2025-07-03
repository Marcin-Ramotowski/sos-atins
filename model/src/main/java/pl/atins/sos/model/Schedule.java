package pl.atins.sos.model;

import jakarta.persistence.*;

@Entity
@IdClass(ScheduleId.class)
public class Schedule {

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private UniversityClass universityClass;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(UniversityClass universityClass) {
        this.universityClass = universityClass;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "student=" + student +
                ", clazz=" + universityClass +
                '}';
    }
}
