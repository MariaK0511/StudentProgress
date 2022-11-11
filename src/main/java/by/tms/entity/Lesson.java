package by.tms.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long lesson;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;

    public Lesson() {
    }

    public Lesson(long id, long lesson, Subject subject) {
        this.id = id;
        this.lesson = lesson;
        this.subject = subject;
    }

    public long getLesson() {
        return lesson;
    }

    public void setLesson(long lesson) {
        this.lesson = lesson;
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

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lesson=" + lesson +
                ", subject=" + subject +
                '}';
    }
}