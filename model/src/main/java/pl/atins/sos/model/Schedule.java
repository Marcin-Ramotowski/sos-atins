package pl.atins.sos.model;

import jakarta.persistence.*;

@Entity
@IdClass(ScheduleId.class)
public class Schedule {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private Class clazz;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "student=" + student +
                ", clazz=" + clazz +
                '}';
    }
}
