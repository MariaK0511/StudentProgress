package by.tms.controlller;

import by.tms.dao.StudentDao;
import by.tms.dao.TeacherDao;
import by.tms.entity.Grade;
import by.tms.entity.Student;
import by.tms.entity.Subject;
import by.tms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class TeacherController {

    class TeacherDto {

        public TeacherDto() {
        }

        private String name;
        private String surname;
        private String subject;

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
    }

    @Autowired
    TeacherDao teacherDao;


    @GetMapping("/teacher")
    public String reg() {
        return "teacher";
    }

   	@Transactional
    @PostMapping("/teacher")
    public String reg(TeacherDto teacherDto) {
        List<Subject> subjects = new ArrayList<>();

        Subject subject = new Subject();
        subject.setSubject(teacherDto.subject);
        subjects.add(subject);


        Teacher teacher = new Teacher();

        teacher.setName(teacherDto.name);
        teacher.setSurname(teacherDto.surname);
        teacher.setSubjects(subjects);
        //     student.setGrades(grades);

        // studentDao.save(student, grade);
        teacherDao.save(teacher);
        return "teacher";
    }

}
