package by.tms.controlller;

import by.tms.dao.StudentDao;
import by.tms.dao.SubjectDao;
import by.tms.dto.StudentDto;
import by.tms.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentDao studentDao;

    @Autowired
    private SubjectDao subjectDao;

    @GetMapping("/studentsList")
    public String initPageOfStudents(@ModelAttribute("student") StudentDto studentDto, Model model) {
        model.addAttribute("students", studentDao.findAll());
        return "studentsList";
    }

    @PostMapping("/studentsList")
    public String addStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "studentsList";
        }
        studentDao.save(studentDto);
        return "redirect:/studentsList";
    }

    @GetMapping("/studentInf/{id}")
    public String getStudentById(@PathVariable("id") long id,
                                 @ModelAttribute("subject") SubjectDto subjectDto,
                                 Model model) {
        model.addAttribute("student", studentDao.findById(id));
        model.addAttribute("subjects", subjectDao.findSubjectsByStudentId(id));
        return "studentInf";
    }

    @PostMapping("/studentInf")
    public String update(@Valid @ModelAttribute("student") StudentDto studentDto,
                         BindingResult bindingResult) {
        studentDao.edit(studentDto);
        return "redirect:/studentInf/" + studentDto.getId();
    }

    @PostMapping("/studentInf/{id}")
    public String delete(@PathVariable("id") long id) {
        studentDao.delete(id);
        return "redirect:/studentsList";
    }

    @GetMapping("/studentPage")
    public String initStudentPage(@ModelAttribute("student") StudentDto studentDto,
                                  Model model) {
        return "studentPage";
    }

    @PostMapping("/studentPage")
    public String findStudentIdByNameAndSurname(@ModelAttribute StudentDto studentDto, Model model) {
        Long id = studentDao.findStudentIdByNameAndSurname(studentDto);
        return "redirect:/studentPerformance/" + id;
    }

    @GetMapping("/studentPerformance/{id}")
    public String showStudentPerformance(@PathVariable("id") Long id,
                                         @ModelAttribute StudentDto studentDto,
                                         Model model) {
        model.addAttribute("student", studentDao.findById(id));
        model.addAttribute("subjects", subjectDao.findSubjectsByStudentId(id));
        return "studentPerformance";
    }
}