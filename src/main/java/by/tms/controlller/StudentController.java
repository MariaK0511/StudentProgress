package by.tms.controlller;

import by.tms.dao.StudentDao;
import by.tms.dto.StudentDto;
import by.tms.dto.TeacherDto;
import by.tms.entity.Grade;
import by.tms.entity.Student;
import by.tms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {
    @Autowired
    StudentDao studentDao;

    @GetMapping("/student")
    public String add(){
        return "student";
    }
    @PostMapping("/student")
    public String add(@Valid @ModelAttribute("newStudent") StudentDto studentDto,
                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "student";
        }
        studentDao.save(studentDto);
   //     model.addAttribute("student", studentDto);
        return "/studentList";
    }

    @GetMapping("/studentList")
    public String getStudentList(@ModelAttribute StudentDto studentDto, Model model) {
        List<Student> students = studentDao.findAll();
        model.addAttribute("students", students);
        return "studentList";
    }
    @GetMapping("/studentForm")
    public String getTeacherById(long id, Model model) {
        model.addAttribute("student", studentDao.findById(id));
        return "/student";
    }

    @PatchMapping("student")
    public String update(@Valid @ModelAttribute StudentDto studentDto, Student student,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "";
        model.addAttribute("student", studentDao.update(studentDto.getId(), student));
        return "/updateStudent";
    }

    @DeleteMapping("studentForm/{id}")
    public String delete(@PathVariable("id") long id) {
        studentDao.delete(id);
        return "/studentForm";
    }
}