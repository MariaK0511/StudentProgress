package by.tms.entity;

import javax.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long grade;

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    private Subject subject;

    @OneToOne(cascade = CascadeType.ALL )
    private Lesson lesson;

    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    public Grade() {
    }

    public Grade(long id, long grade, Student student, Subject subject, Lesson lesson, Teacher teacher) {
        this.id = id;
        this.grade = grade;
        this.student = student;
        this.subject = subject;
        this.lesson = lesson;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", student=" + student +
                ", subject=" + subject +
                ", lesson=" + lesson +
                ", teacher=" + teacher +
                '}';
    }
}
