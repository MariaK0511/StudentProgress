package by.tms.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Lesson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numOfLesson;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;

    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    public Lesson() {
    }

    public Lesson(long id, Subject subject, Teacher teacher) {
        this.id = id;
        this.subject = subject;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


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

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subject=" + subject +
                ", teacher=" + teacher +
                '}';
    }
}
