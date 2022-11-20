package by.tms.entity;

import javax.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long grade;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;

    private String lesson;


    public Grade() {
    }

    public Grade(long id, long grade, Student student, Teacher teacher, Subject subject, String lesson) {
        this.id = id;
        this.grade = grade;
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.lesson = lesson;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", student=" + student +
                ", teacher=" + teacher +
                ", subject=" + subject +
                ", lesson='" + lesson + '\'' +
                '}';
    }
}