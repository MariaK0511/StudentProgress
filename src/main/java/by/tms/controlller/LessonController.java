package by.tms.controlller;


import by.tms.dao.LessonDao;
import by.tms.entity.Grade;
import by.tms.entity.Lesson;
import by.tms.entity.Subject;
import by.tms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Date;

@Controller
@RequestMapping("/")
public class LessonController {

    class LessonDto {
        private Date date;
        private Subject subject;
        private Teacher teacher;

        public LessonDto() {
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }
    }

    @Autowired
    LessonDao lessonDao;

    @GetMapping("/lesson")
    public String reg() {
        return "lesson";
    }

    @Transactional
    @PostMapping("/lesson")
    public String reg(LessonDto lessonDto) {
        Lesson lesson = new Lesson();
        lesson.setDate(lessonDto.date);
        lesson.setSubject(lessonDto.subject);
        lesson.setTeacher(lessonDto.teacher);
        lessonDao.save(lesson);
        return "lesson";
    }

}
