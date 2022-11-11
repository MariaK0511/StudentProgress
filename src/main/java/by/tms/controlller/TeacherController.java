package by.tms.controlller;

import by.tms.dao.SubjectDao;
import by.tms.dao.TeacherDao;
import by.tms.dto.StudentDto;
import by.tms.dto.SubjectDto;
import by.tms.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class TeacherController {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private SubjectDao subjectDao;

    @GetMapping("/teachersList")
    public String initPageOfTeachers(@ModelAttribute("teacher") TeacherDto teacherDto,
                                     Model model) {
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
    public String getTeacherById(@PathVariable("id") long id,
                                 @ModelAttribute("subject") SubjectDto subjectDto,
                                 Model model) {
        model.addAttribute("teacher", teacherDao.findById(id));
        model.addAttribute("subjects", subjectDao.findSubjectsByTeacherId(id));
        return "teacherInf";
    }

    @PostMapping("/teacherInf")
    public String update(@Valid @ModelAttribute("teacher") TeacherDto teacherDto,
                         BindingResult bindingResult) {
        teacherDao.edit(teacherDto);
        return "redirect:/teacherInf/" + teacherDto.getId();
    }

    @PostMapping("/teacherInf/{id}")
    public String delete(@PathVariable("id") long id) {
        teacherDao.delete(id);
        return "redirect:/teachersList";
    }

    @GetMapping("/teacherPage")
    public String initTeacherPage(@ModelAttribute("teacher") TeacherDto teacherDto) {
        return "teacherPage";
    }

    @PostMapping("/teacherPage")
    public String findTeacherIdByNameAndSurname(@ModelAttribute TeacherDto teacherDto, Model model) {
        Long id = teacherDao.findTeacherIdByNameAndSurname(teacherDto);
        return "redirect:/teacherBook/" + id;
    }

    @GetMapping("/teacherBook/{id}")
    public String showTeacherGradeBook(@PathVariable("id") Long id,
                                       @ModelAttribute TeacherDto teacherDto,
                                       Model model) {
        model.addAttribute("teacher", teacherDao.findById(id));
        model.addAttribute("subjects", subjectDao.findSubjectsByTeacherId(id));
        return "teacherBook";
    }
}