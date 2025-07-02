package pl.atins.sos.model;


import java.io.Serializable;

public class ScheduleId implements Serializable {
    private Long student;
    private Long universityClass;

    public ScheduleId() {
    }

    public ScheduleId(Long student, Long universityClass) {
        this.student = student;
        this.universityClass = universityClass;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(Long universityClass) {
        this.universityClass = universityClass;
    }
}
