package by.tms.entity;

import javax.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
    @OneToOne(cascade = CascadeType.ALL )
    private Lesson lesson;


    public Grade() {
    }

    public Grade(long id, long name, Student student, Teacher teacher, Subject subject, Lesson lesson) {
        this.id = id;
        this.name = name;
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

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name=" + name +
                ", student=" + student +
                ", teacher=" + teacher +
                ", subject=" + subject +
                ", lesson=" + lesson +
                '}';
    }
}