package pl.atins.sos.model;


public class ScheduleId {
    private Long student;
    private Long clazz;

    public ScheduleId() {
    }

    public ScheduleId(Long student, Long clazz) {
        this.student = student;
        this.clazz = clazz;
    }

    public Long getStudent() {
        return student;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public Long getClazz() {
        return clazz;
    }

    public void setClazz(Long clazz) {
        this.clazz = clazz;
    }
}
