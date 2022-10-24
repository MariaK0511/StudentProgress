package by.tms.controlller;

import by.tms.dao.StudentDao;
import by.tms.dto.StudentDto;
import by.tms.dto.TeacherDto;
import by.tms.entity.Grade;
import by.tms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "student";
        }
        studentDao.save(studentDto);
        return "redirect:/studentInf";
    }

//    @GetMapping("/student")
//    public String findAll(){
//        return "student";
//    }
//
//    @Transactional
//    @PostMapping("/student")
//    public String save(StudentDto studentDto) {
//        Student student = new Student();
//        student.setName(studentDto.name);
//        student.setSurname(studentDto.surname);
//        studentDao.save(student);
//        return "student";
//    }
}
