package by.tms.dto;

import by.tms.entity.Lesson;
import by.tms.entity.Student;
import by.tms.entity.Subject;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GradeDto {
    public GradeDto() {
    }
    private long id;
    @NotNull
    private long name;

    private Student student;
private Subject subject;

    private Lesson lesson;

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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
