package by.tms.service;

import by.tms.dao.StudentDao;
import by.tms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public StudentService() {
    }

//    public void saveStudent(Student student) {
//        studentDao.save(student);
//    }

    public Student findStudent(long id) {
        return studentDao.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    public void updateStudent(Student student) {
        studentDao.update(student);
    }
}