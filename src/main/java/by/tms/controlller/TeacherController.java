package by.tms.controlller;

import by.tms.dao.StudentDao;
import by.tms.dao.TeacherDao;
import by.tms.dto.TeacherDto;
import by.tms.entity.Grade;
import by.tms.entity.Student;
import by.tms.entity.Subject;
import by.tms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class TeacherController {
    @Autowired
    TeacherDao teacherDao;

    @GetMapping("/teacher")
    public String add(@ModelAttribute("newTeacher") TeacherDto teacherDto) {
        return "teacher";
    }

    @PostMapping("/teacher")
    public String add(@Valid @ModelAttribute TeacherDto teacherDto, BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            return "teacher";
        }
        teacherDao.save(teacherDto);
        model.addAttribute("teacher", teacherDto);
        return "teacherInf";
    }
    @GetMapping("/teacher")
    public String getAllTeachers(@ModelAttribute TeacherDto teacherDto, Model model) {
        List<Teacher> teachers = teacherDao.findAll();
        model.addAttribute("teachers", teachers);
        return "teacher";
    }

    @GetMapping("/teacher")
    public String getTeacherById(@ModelAttribute long id, Model model) {
        model.addAttribute("CurrentTeacher", teacherDao.findById(id));
        return "teacher";
    }

    @GetMapping("/teacher")
    public String edit(@ModelAttribute("currentTeacher") TeacherDto teacherDto) {
        return "teacher";
    }

    @PostMapping("/teacher")
    public String add(@ModelAttribute TeacherDto teacherDto, Teacher teacher,
                      Model model) {
        model.addAttribute("teacher",   teacherDao.edit(teacherDto.getId(), teacher));
        return "teacherInf";
    }

    @GetMapping("/teacher")
    public String delete(@ModelAttribute("currentTeacher") TeacherDto teacherDto) {
        teacherDao.delete(teacherDto.getId());
        return "teacher";
    }

//    @RequestMapping("teacherInfo") //localhost:8080/calc/info
//    public String userInfo(Model model, HttpSession httpSession) {
//        TeacherDto teacherDto = (TeacherDto) httpSession.getAttribute("teacher");
//        model.addAttribute("teacher", teacherDto);
//        return "info";
//    }
}
