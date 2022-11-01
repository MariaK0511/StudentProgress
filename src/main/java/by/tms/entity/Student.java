package by.tms.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    public Student() {
    }

    public Student(long id, String name, String surname, List<Grade> grades) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    //    this.grades = grades;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

   // public List<Grade> getGrades() {
//        return grades;
//    }

//    public void setGrades(List<Grade> grades) {
//        this.grades = grades;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
               // ", grades=" + grades +
                '}';
    }
}
