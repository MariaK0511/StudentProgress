package by.tms.controlller;


import by.tms.dao.GradeDao;
import by.tms.entity.*;
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
public class GradeController {

    class GradeDto{
        public GradeDto() {
        }
        private long grade;
        private Student student;
        private Subject subject;
        private Lesson lesson;
        private Teacher teacher;

        public long getGrade() {
            return grade;
        }

        public void setGrade(long grade) {
            this.grade = grade;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Lesson getLesson() {
            return lesson;
        }

        public void setLesson(Lesson lesson) {
            this.lesson = lesson;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }
    }

   @Autowired
   GradeDao gradeDao;

    @GetMapping("/grade")
    public String reg() {
        return "grade";
    }

    @Transactional
    @PostMapping("/grade")
    public String reg(GradeDto gradeDto) {
       Grade grade = new Grade();
       grade.setGrade(gradeDto.grade);
       grade.setStudent(gradeDto.student);
       grade.setSubject(gradeDto.subject);
       grade.setLesson(gradeDto.lesson);
       grade.setTeacher(gradeDto.teacher);

       gradeDao.save(grade);
        return "grade";
    }

}
