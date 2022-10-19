package by.tms.dto;

import org.springframework.stereotype.Component;

@Component
public class StudentDto {
    public StudentDto() {
    }

    private String name;
    private String surname;
    private long grade;

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
}
