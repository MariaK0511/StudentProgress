package by.tms.dto;

import by.tms.entity.Lesson;
import by.tms.entity.Student;

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
    private long grade;

    private Student student;


    private Lesson lesson;

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
}
