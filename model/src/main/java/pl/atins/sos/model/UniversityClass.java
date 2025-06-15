package pl.atins.sos.model;

import jakarta.persistence.*;

import java.time.OffsetTime;

@Entity
@Table(name = "Class")
public class UniversityClass extends BaseEntity {

    // TODO: add one-to-many for Schedule once implemented

    @ManyToOne(optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "week_day", nullable = false)
    private String weekDay;

    @Column(name = "start_time", nullable = false)
    private OffsetTime startTime;

    @Column(name = "end_time", nullable = false)
    private OffsetTime endTime;

    @Column(name = "room")
    private String room;

    @Column(name = "type")
    private String type;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public OffsetTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetTime startTime) {
        this.startTime = startTime;
    }

    public OffsetTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetTime endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Class{" +
                "subject=" + subject +
                ", teacher=" + teacher +
                ", weekDay='" + weekDay + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", room='" + room + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
