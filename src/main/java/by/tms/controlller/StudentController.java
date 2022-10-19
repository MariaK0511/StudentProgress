package by.tms.controlller;

import by.tms.dao.StudentDao;
import by.tms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    class StudentDto {


        private String name;
        private String surname;
        public StudentDto() {
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
    }

    @Autowired
    StudentDao studentDao;

    @GetMapping("/student")
    public String save(){
        return "student";
    }

    @Transactional
    @PostMapping("/student")
    public String save(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.name);
        student.setSurname(studentDto.surname);
        studentDao.save(student);
        return "student";
    }

//    @GetMapping("/student")
//    public String findAll(){
//        return "student";
//    }
//
//    @Transactional
//    @PostMapping("/student")
//    public String save(StudentDto studentDto) {
//        Student student = new Student();
//        student.setName(studentDto.name);
//        student.setSurname(studentDto.surname);
//        studentDao.save(student);
//        return "student";
//    }
}
