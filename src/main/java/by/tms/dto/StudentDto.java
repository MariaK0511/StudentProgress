package by.tms.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
public class StudentDto {
    public StudentDto() {
    }
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Name should not be empty")
    private String surname;
    private long grade;

    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
