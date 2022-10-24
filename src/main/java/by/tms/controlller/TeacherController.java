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
//    @PostMapping("/teacher")
//    public String add(@Valid @ModelAttribute("newTeacher") TeacherDto teacherDto,
//                      BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "teacher";
//        }
//        teacherDao.save(teacherDto);
//        return "redirect:/teacherInf";
//    }
    @PostMapping("/teacher")
    public String show(@Valid @ModelAttribute TeacherDto teacherDto,BindingResult bindingResult,
                       Model model){
        if (bindingResult.hasErrors()) {
            return "teacher";
        }
        teacherDao.save(teacherDto);
        model.addAttribute("teacher", teacherDto);
        return "teacherInf";
    }

//    @GetMapping("/{id}")
//    public String teacherInfo(@PathVariable("id") long id, Model model) {
//        model.addAttribute("teacher", teacherDao.show(id));
//        return "teacherInf";
//    }

//    @RequestMapping("teacherInfo") //localhost:8080/calc/info
//    public String userInfo(Model model, HttpSession httpSession) {
//        TeacherDto teacherDto = (TeacherDto) httpSession.getAttribute("teacher");
//        model.addAttribute("teacher", teacherDto);
//        return "info";
//    }
}
