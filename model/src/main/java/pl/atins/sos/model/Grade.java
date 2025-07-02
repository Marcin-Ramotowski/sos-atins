package pl.atins.sos.model;

import jakarta.persistence.*;

@Entity
public class Grade extends BaseEntity {

    @Column(name = "comment")
    private String comment;

    @Column(name = "grade", nullable = false)
    private double grade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "transcript_id", nullable = false)
    private Transcript transcript;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "comment='" + comment + '\'' +
                ", grade=" + grade +
                ", teacher=" + teacher +
                ", transcript=" + transcript +
                '}';
    }
}
