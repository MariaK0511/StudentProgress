package by.tms.dao;

import by.tms.dto.StudentDto;
import by.tms.entity.Grade;
import by.tms.entity.Student;
import by.tms.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(StudentDto studentDto) {
        Session session = sessionFactory.getCurrentSession(); //generate new session
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        session.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() { // JPQL
        Session session = sessionFactory.getCurrentSession();
        List<Student> students = session.createQuery("from Student ", Student.class).getResultList();
        return students;
    }

    @Transactional(readOnly = true)
    public Student findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.find(Student.class, id);
        return student;
    }

    @Transactional
    public void update(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }

//    @Transactional(readOnly = true)
//    public Teacher findBySurname(String surname) {
//        Session session = sessionFactory.getCurrentSession();
//        Teacher teacher = session.createQuery("from Teacher  where surname = :teacher", Teacher.class)
//                .setParameter("teacher", surname).getSingleResult();
//        return teacher;
//    }
@Autowired
private StudentDao studentDao;

 

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


