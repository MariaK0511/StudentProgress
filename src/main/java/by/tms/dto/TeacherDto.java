package by.tms.dto;

import javax.validation.constraints.NotEmpty;

public class TeacherDto {

    public TeacherDto() {
    }
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    private String subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}