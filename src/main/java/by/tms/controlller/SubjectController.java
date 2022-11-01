package by.tms.controlller;


import by.tms.dao.SubjectDao;
import by.tms.dto.SubjectDto;
import by.tms.dto.TeacherDto;
import by.tms.entity.Lesson;
import by.tms.entity.Subject;
import by.tms.entity.Teacher;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/")
public class SubjectController {
    @Autowired
    SubjectDao subjectDao;

    @GetMapping("/subjectsList")
    public String initPageOfSubjects(@ModelAttribute("subject") SubjectDto subjectDto, Model model) {
        model.addAttribute("subjects", subjectDao.findAll());
        return "subjectsList";
    }

    @PostMapping("/subjectsList")
    public String addSubject(@Valid @ModelAttribute("subject") SubjectDto subjectDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "subjectsList";
        }
        subjectDao.save(subjectDto);
        return "redirect:/subjectsList";
    }

    @GetMapping("/subjectInf/{id}")
    public String getSubjectById(@PathVariable("id") long id, Model model) {
        model.addAttribute("subject", subjectDao.findById(id));
        return "subjectInf";
    }
    @PostMapping("/subjectInf")
    public String update(@Valid @ModelAttribute("subject") SubjectDto subjectDto,
                         BindingResult bindingResult, Model model) {
        subjectDao.edit(subjectDto);
        return "redirect:/subjectInf/" + subjectDto.getId();
    }
    @PostMapping("/subjectInf/{id}")
    public String delete(@PathVariable("id") long id) {
       subjectDao.delete(id);
        return "redirect:/subjectsList";
    }

}