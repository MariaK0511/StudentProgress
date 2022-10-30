package by.tms.controlller;


import by.tms.dao.GradeDao;
import by.tms.dto.GradeDto;
import by.tms.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class GradeController {

    @Autowired
    private  GradeDao gradeDao;




}
