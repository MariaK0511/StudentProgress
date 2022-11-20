package by.tms.controlller;


import by.tms.dao.GradeDao;
import by.tms.dao.StudentDao;
import by.tms.dao.SubjectDao;
import by.tms.dto.GradeDto;
import by.tms.dto.StudentDto;
import by.tms.dto.SubjectDto;
import by.tms.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class GradeController {

    @Autowired
    private  GradeDao gradeDao;
    @Autowired
    SubjectDao subjectDao;
    @Autowired
    StudentDao studentDao;



    @GetMapping("/studentGrades/{studentId}/{subjectId}")
    public String initPageOfStudentGrades(@PathVariable("studentId") long studentId,
                                          @PathVariable("subjectId") long subjectId,
                                          Model model) {
        model.addAttribute("subject", subjectDao.findById(subjectId));
        model.addAttribute("grades", gradeDao.findGradesByStudentIdAndSubjectId(studentId,subjectId));
        return "studentGrades";
    }
}