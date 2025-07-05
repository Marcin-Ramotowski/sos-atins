package pl.atins.sos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Student")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(name = "agreement_num", nullable = false)
    private int agreementNum;

    @Column(name = "avg_score", nullable = false)
    private double avgScore;

    @Column(name = "title_of_grade", nullable = false)
    @Enumerated(EnumType.STRING)
    private TitleOfGrade titleOfGrade;

    @Column(name = "current_semester", nullable = false)
    private int currentSemester;

    @Column(name = "graduation_date", nullable = false)
    private LocalDate graduationDate;

    @Column(name = "enrollment_year", nullable = false)
    private LocalDate enrollmentYear;

    @Column(name = "enroll_semester", nullable = false)
    private int enrollSemester;

    @Column(name = "mode_of_study", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModeOfStudy modeOfStudy;

    @Column(name = "scholarship_holder", nullable = false)
    private boolean scholarshipHolder;

    @Column(name = "specialization", nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Column(name = "student_number", nullable = false, unique = true)
    private int studentNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public int getAgreementNum() {
        return agreementNum;
    }

    public void setAgreementNum(int agreementNum) {
        this.agreementNum = agreementNum;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public TitleOfGrade getTitleOfGrade() {
        return titleOfGrade;
    }

    public void setTitleOfGrade(TitleOfGrade titleOfGrade) {
        this.titleOfGrade = titleOfGrade;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public LocalDate getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(LocalDate enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getEnrollSemester() {
        return enrollSemester;
    }

    public void setEnrollSemester(int enrollmentSemester) {
        this.enrollSemester = enrollmentSemester;
    }

    public ModeOfStudy getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(ModeOfStudy modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
    }

    public boolean isScholarshipHolder() {
        return scholarshipHolder;
    }

    public void setScholarshipHolder(boolean scholarshipHolder) {
        this.scholarshipHolder = scholarshipHolder;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
