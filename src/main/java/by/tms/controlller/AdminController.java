package by.tms.controlller;

import by.tms.dto.TeacherDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {
    @GetMapping("/adminPage")
    public String showAdminPage() {
        return "adminPage";
    }
}