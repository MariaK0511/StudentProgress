package by.tms.controlller;

import by.tms.dao.StudentDao;
import by.tms.dao.TeacherDao;
import by.tms.entity.Grade;
import by.tms.entity.Student;
import by.tms.entity.Teacher;
import by.tms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    class StudentDto {

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

    @Autowired
    StudentDao studentDao;


    @GetMapping("/student")
    public String reg() {
        return "student";
    }

    //	@Transactional
    @PostMapping("/student")
    public String reg(StudentDto studentDto) {
        List<Grade> grades = new ArrayList<>();

        Grade grade = new Grade();
        grade.setGrade(studentDto.grade);

        grades.add(grade);

        Student student = new Student();
        student.setName(studentDto.name);
        student.setSurname(studentDto.surname);
        //     student.setGrades(grades);

        // studentDao.save(student, grade);
        studentDao.save(student);
        return "student";
    }


}
