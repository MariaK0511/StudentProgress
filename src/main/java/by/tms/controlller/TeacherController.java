package by.tms.controlller;

import by.tms.dao.TeacherDao;
import by.tms.dto.TeacherDto;
import by.tms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class TeacherController {
    @Autowired
    TeacherDao teacherDao;

    @GetMapping("/teacher")
    public String newTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) {
        return "teacher";
    }
    @PostMapping("/teacher")
    public String add(@Valid @ModelAttribute("teacher") TeacherDto teacherDto, BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            return "teacher";
        }
        teacherDao.save(teacherDto);
        model.addAttribute("teacher", teacherDto);
        return "redirect:/teacherInf";
    }
    @GetMapping("/allTeachers")
    public String getAllTeachers(@ModelAttribute TeacherDto teacherDto, Model model) {
        List<Teacher> teachers = teacherDao.findAll();
        model.addAttribute("teachers", teachers);
        return "allTeachers";
    }
    @GetMapping("/teacherInf")
    public String getTeacherById(long id, Model model) {
        model.addAttribute("teacher", teacherDao.findById(id));
        return "teacherInf";
    }

    @PatchMapping("/edit")
    public String update(@Valid @ModelAttribute TeacherDto teacherDto, Teacher teacher,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "people/edit";
        model.addAttribute("teacher", teacherDao.edit(teacherDto.getId(), teacher));
        return "redirect:/teacherInf";
    }

    @DeleteMapping("/teacherInf/{id}")
    public String delete(@PathVariable("id") long id) {
        teacherDao.delete(id);
        return "teacherInf";
    }
}