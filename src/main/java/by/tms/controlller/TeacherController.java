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
    private TeacherDao teacherDao;

    @GetMapping("/teachersList")
    public String initPageOfTeachers(@ModelAttribute("teacher") TeacherDto teacherDto, Model model) {
        model.addAttribute("teachers", teacherDao.findAll());
        return "teachersList";
    }

    @PostMapping("/teachersList")
    public String addTeacher(@Valid @ModelAttribute("teacher") TeacherDto teacherDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teachersList";
        }
        teacherDao.save(teacherDto);
        return "redirect:/teachersList";
    }

    @GetMapping("/teacherInf/{id}")
    public String getTeacherById(@PathVariable("id") long id, Model model) {
        model.addAttribute("teacher", teacherDao.findById(id));
        return "teacherInf";
    }

    @PostMapping("/teacherInf")
    public String update(@Valid @ModelAttribute("teacher") TeacherDto teacherDto,
                         BindingResult bindingResult, Model model) {
        teacherDao.edit(teacherDto);
        return "redirect:/teacherInf/" + teacherDto.getId();
    }

    @PostMapping("/teacherInf/{id}")
    public String delete(@PathVariable("id") long id) {
        teacherDao.delete(id);
        return "redirect:/teachersList";
    }
}